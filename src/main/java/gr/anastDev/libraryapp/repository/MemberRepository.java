package gr.anastDev.libraryapp.repository;

import gr.anastDev.libraryapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Optional<Member> findByUuid(String uuid);
    Optional<Member> findByLastname(String lastname);
    Optional<Member> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
