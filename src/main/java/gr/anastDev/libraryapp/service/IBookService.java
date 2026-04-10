package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BookEditDTO;
import gr.anastDev.libraryapp.dto.BookInsertDTO;
import gr.anastDev.libraryapp.model.Book;

public interface IBookService {

    Book saveBook(BookInsertDTO bookInsertDTO) throws EntityInvalidArgumentException, EntityAlreadyExistsException;
    void updateBook(BookEditDTO dto) throws EntityAlreadyExistsException, EntityInvalidArgumentException, EntityNotFoundException;
    void deleteBookByISBN(String isbn) throws EntityNotFoundException;
}
