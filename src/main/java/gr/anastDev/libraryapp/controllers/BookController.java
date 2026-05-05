package gr.anastDev.libraryapp.controllers;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.*;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Book;
import gr.anastDev.libraryapp.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final Mapper mapper;

    @Operation(
            summary = "Get all books",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Books retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookReadOnlyDTO.class))
                    ),
            }
    )
    @GetMapping
    public ResponseEntity<List<BookReadOnlyDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @Operation(
            summary = "Save a book",
            responses = {
                    @ApiResponse(
                            responseCode = "201", description = "Book created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MemberReadOnlyDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "409", description = "Book already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
//                    @ApiResponse(
//                            responseCode = "401", description = "Unauthorized",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class)
//                            )
//                    ),
//                    @ApiResponse(
//                            responseCode = "403", description = "Access Denied",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
//                    )
            }
    )
    @PostMapping
    public ResponseEntity<BookReadOnlyDTO> createBook(@Valid @RequestBody BookInsertDTO dto) throws EntityAlreadyExistsException, EntityInvalidArgumentException {
        Book book = bookService.saveBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToBookReadOnlyDTO(book));
    }

    @Operation(
            summary = "Get a specific book",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Book retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookReadOnlyDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Book not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
//                    @ApiResponse(
//                            responseCode = "401", description = "Unauthorized",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class)
//                            )
//                    ),
//                    @ApiResponse(
//                            responseCode = "403", description = "Access Denied",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
//                    )
            }
    )
    @GetMapping("/{isbn}")
    public ResponseEntity<BookReadOnlyDTO> getBookByISBN(@PathVariable  String isbn) throws EntityNotFoundException {
        BookReadOnlyDTO dto = bookService.getBookByISBN(isbn);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Update a book",
            responses = {
                    @ApiResponse(
                            responseCode = "204", description = "Book updated successfully",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "409", description = "Book already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
//                    @ApiResponse(
//                            responseCode = "401", description = "Unauthorized",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class)
//                            )
//                    ),
//                    @ApiResponse(
//                            responseCode = "403", description = "Access Denied",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
//                    )
            }
    )
    @PutMapping
    public ResponseEntity<Void> updateBook(@Valid @RequestBody BookUpdateDTO dto) throws EntityNotFoundException, EntityAlreadyExistsException {
        bookService.updateBook(dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete a book",
            responses = {
                    @ApiResponse(
                            responseCode = "204", description = "Book deleted",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Book not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
            }
    )
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) throws EntityNotFoundException {
        bookService.deleteBookByISBN(isbn);
        return ResponseEntity.noContent().build();
    }
}
