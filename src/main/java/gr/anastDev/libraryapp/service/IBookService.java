package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BookEditDTO;
import gr.anastDev.libraryapp.dto.BookInsertDTO;
import gr.anastDev.libraryapp.dto.BookReadOnlyDTO;
import gr.anastDev.libraryapp.model.Book;

import java.util.List;

public interface IBookService {

    List<BookReadOnlyDTO> getAllBooks();
    BookReadOnlyDTO getBookByISBN(String isbn) throws EntityNotFoundException;
    Book saveBook(BookInsertDTO bookInsertDTO) throws EntityAlreadyExistsException;
    void updateBook(BookEditDTO dto) throws EntityAlreadyExistsException, EntityInvalidArgumentException, EntityNotFoundException;
    void deleteBookByISBN(String isbn) throws EntityNotFoundException;
}
