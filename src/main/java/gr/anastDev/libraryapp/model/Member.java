package gr.anastDev.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "members")
public class Member extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String firstname;
    private String lastname;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

    @PrePersist
   public void initializeUUID(){
       if (uuid == null) uuid = UUID.randomUUID().toString();
   }

    public void addBook(Book book) {
        if (books == null) books = new HashSet<>();
        books.add(book);
        book.setMember(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setMember(null);
    }
}
