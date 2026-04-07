package gr.anastDev.libraryapp.repository;

import gr.anastDev.libraryapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Optional<Member> findByUuid(String uuid);
    Optional<Member> findByLastname(String lastname);


}
