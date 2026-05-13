package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BookUpdateDTO;
import gr.anastDev.libraryapp.dto.BookInsertDTO;
import gr.anastDev.libraryapp.dto.BookReadOnlyDTO;
import gr.anastDev.libraryapp.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookService {

    List<BookReadOnlyDTO> getAllBooks();
    BookReadOnlyDTO getBookByISBN(String isbn) throws EntityNotFoundException;
    Book saveBook(BookInsertDTO bookInsertDTO) throws EntityAlreadyExistsException;
    void updateBook(BookUpdateDTO dto) throws EntityAlreadyExistsException, EntityNotFoundException;
    void deleteBookByISBN(String isbn) throws EntityNotFoundException;
    Page<BookReadOnlyDTO> getPaginatedBooks(int page, int size);
}
