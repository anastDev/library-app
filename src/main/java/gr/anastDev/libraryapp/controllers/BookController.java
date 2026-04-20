package gr.anastDev.libraryapp.controllers;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BookEditDTO;
import gr.anastDev.libraryapp.dto.BookInsertDTO;
import gr.anastDev.libraryapp.dto.BookReadOnlyDTO;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Book;
import gr.anastDev.libraryapp.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<List<BookReadOnlyDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookReadOnlyDTO> createBook(@Valid @RequestBody BookInsertDTO dto) throws EntityAlreadyExistsException, EntityInvalidArgumentException {
        Book book = bookService.saveBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToBookReadOnlyDTO(book));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookReadOnlyDTO> getBookByISBN(@PathVariable  String isbn) throws EntityNotFoundException {
        BookReadOnlyDTO dto = bookService.getBookByISBN(isbn);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<Void> updateBook(@Valid @RequestBody BookEditDTO dto) throws EntityNotFoundException, EntityInvalidArgumentException, EntityAlreadyExistsException {
        bookService.updateBook(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) throws EntityNotFoundException {
        bookService.deleteBookByISBN(isbn);
        return ResponseEntity.noContent().build();
    }
}
