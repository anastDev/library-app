package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BorrowedInsertDTO;
import gr.anastDev.libraryapp.dto.BorrowedReadOnlyDTO;
import gr.anastDev.libraryapp.model.Borrowed;

import java.util.List;

public interface IBorrowedService {

    Borrowed borrowBook(BorrowedInsertDTO dto) throws EntityNotFoundException, EntityInvalidArgumentException;
    List<BorrowedReadOnlyDTO> getBorrowedByMemberUuid(String uuid) throws EntityNotFoundException;
    List<BorrowedReadOnlyDTO> getBorrowedByBookIsbn(String isbn) throws EntityNotFoundException;
}
