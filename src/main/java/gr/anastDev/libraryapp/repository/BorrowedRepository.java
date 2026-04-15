package gr.anastDev.libraryapp.repository;

import gr.anastDev.libraryapp.model.Book;
import gr.anastDev.libraryapp.model.Borrowed;
import gr.anastDev.libraryapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowedRepository extends JpaRepository<Borrowed, Long> {
    List<Borrowed> findByMember(Member member);
    List<Borrowed> findByBook(Book book);
    Optional<Borrowed> findByMemberAndBook(Member member, Book book);
}
