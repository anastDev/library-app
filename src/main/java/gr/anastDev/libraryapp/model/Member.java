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

    @OneToMany(mappedBy = "member")
    private Set<Borrowed> borrowed = new HashSet<>();

    @PrePersist
   public void initializeUUID(){
       if (uuid == null) uuid = UUID.randomUUID().toString();
   }

    public void addBorrow(Borrowed b) {
        borrowed.add(b);
        b.setMember(this);
    }

    public void removeBorrow(Borrowed b) {
        borrowed.remove(b);
        b.setMember(null);
    }
}
