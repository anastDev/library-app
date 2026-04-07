package gr.anastDev.libraryapp.repository;

import gr.anastDev.libraryapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findByIsbn(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findByGenre(String genre);
}
