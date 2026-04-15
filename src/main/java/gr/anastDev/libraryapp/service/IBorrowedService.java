package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BorrowedInsertDTO;
import gr.anastDev.libraryapp.model.Borrowed;

public interface IBorrowedService {

    Borrowed borrowBook(BorrowedInsertDTO dto) throws EntityNotFoundException, EntityInvalidArgumentException;
}
