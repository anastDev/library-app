package gr.anastDev.libraryapp.controllers;

import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BorrowedInsertDTO;
import gr.anastDev.libraryapp.dto.BorrowedReadOnlyDTO;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Borrowed;
import gr.anastDev.libraryapp.service.BorrowedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/borrowed")
public class BorrowedController {

    private final BorrowedService borrowedService;
    private final Mapper mapper;

    @GetMapping("/member/{uuid}")
    public ResponseEntity<List<BorrowedReadOnlyDTO>> getBorrowedByMemberUuid(@PathVariable String uuid)
            throws EntityNotFoundException {
        return ResponseEntity.ok(borrowedService.getBorrowedByMemberUuid(uuid));
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<BorrowedReadOnlyDTO>> getBorrowedByBookIsbn(@PathVariable String isbn)
            throws EntityNotFoundException {
        return ResponseEntity.ok(borrowedService.getBorrowedByBookIsbn(isbn));
    }

    @PostMapping
    public ResponseEntity<BorrowedReadOnlyDTO> borrowBook(@Valid @RequestBody BorrowedInsertDTO dto)
            throws EntityNotFoundException, EntityInvalidArgumentException {
        Borrowed borrowed = borrowedService.borrowBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToBorrowedReadOnlyDTO(borrowed));
    }
}
