package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BookEditDTO;
import gr.anastDev.libraryapp.dto.BookInsertDTO;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Book;
import gr.anastDev.libraryapp.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService implements IBookService{

    private final BookRepository bookRepository;
    private final Mapper mapper;


    @Override
    @Transactional(rollbackOn = EntityAlreadyExistsException.class)
    public Book saveBook(BookInsertDTO dto) throws  EntityAlreadyExistsException {
      try {
          if(dto.getIsbn() != null && bookRepository.findByIsbn(dto.getIsbn()).isPresent()) {
              throw new EntityAlreadyExistsException("Book", "Book with ISBN " + dto.getIsbn() + "already exists.");
          }

          Book book = mapper.mapToBookEntity(dto);
          bookRepository.save(book);
          log.info("Book with ISBN={} saved", dto.getIsbn());
          return book;
      } catch (EntityAlreadyExistsException e) {
          log.error("Save failed for book with ISBN={}, Book already exists.", dto.getIsbn(), e);
          throw e;
      }
    }

    @Override
    @Transactional(rollbackOn = EntityAlreadyExistsException.class)
    public void updateBook(BookEditDTO dto) throws EntityAlreadyExistsException, EntityInvalidArgumentException, EntityNotFoundException {
        try {
            Book book = bookRepository.findByIsbn(dto.getIsbn())
                    .orElseThrow(() -> new EntityNotFoundException("Book", "Book not found"));

            if(!book.getIsbn().equals(dto.getIsbn())) {
                if(bookRepository.findByIsbn(dto.getIsbn()).isPresent()) {
                    book.setIsbn(dto.getIsbn());
                } else throw new EntityAlreadyExistsException("Book", "Book with ISBN " + dto.getIsbn() + "already exists.");
            }

            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setGenre(dto.getGenre());
            book.setDescription(dto.getDescription());
            book.setYearOfPublish(dto.getYearOfPublish());
            book.setNumberOfPages(dto.getNumberOfPages());
            book.setAvailableCopies(dto.getAvailableCopies());

            bookRepository.save(book);
            log.info("Book with isbn={} updated!", dto.getIsbn());

        } catch (EntityNotFoundException e) {
            log.error("Update failed for book with isbn={}. Entity not found.", dto.getIsbn(), e);
            throw e;
        } catch (EntityAlreadyExistsException e) {
            log.error("Update failed for book with isbn={}. Entity already exists.", dto.getIsbn(), e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackOn = EntityNotFoundException.class)
    public void deleteBookByISBN(String isbn) throws EntityNotFoundException {
        try {
            Book book = bookRepository.findByIsbn(isbn)
                    .orElseThrow(() -> new EntityNotFoundException("Book", "Book with ISBN: " + isbn + "not found!" ));

            bookRepository.deleteById(book.getId());

            log.info("Book with ISBN={} deleted!", isbn);
        } catch (EntityNotFoundException e) {
            log.error("Delete failed for book with isbn={}. Book not found.", isbn, e);
        }
    }
}
