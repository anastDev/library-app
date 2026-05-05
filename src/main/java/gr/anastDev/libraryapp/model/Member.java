package gr.anastDev.libraryapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "members")
public class Member extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
