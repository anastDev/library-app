package gr.anastDev.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "books")
public class Book extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String isbn;

    private String title;
    private String author;
    private String genre;
    private String description;
    private Instant yearOfPublish;
    private int numberOfPages;
    private int availableCopies;

    @OneToMany(mappedBy = "book")
    private Set<Borrowed> borrowed = new HashSet<>();

    public void addBorrow(Borrowed b) {
        borrowed.add(b);
        b.setBook(this);
    }

    public void removeBorrow(Borrowed b) {
        borrowed.remove(b);
        b.setMember(null);
    }
}
