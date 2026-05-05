package gr.anastDev.libraryapp.controllers;

import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BorrowedInsertDTO;
import gr.anastDev.libraryapp.dto.BorrowedReadOnlyDTO;
import gr.anastDev.libraryapp.dto.ResponseMessageDTO;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Borrowed;
import gr.anastDev.libraryapp.service.BorrowedService;
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
@RequestMapping("/api/borrowed")
public class BorrowedController {

    private final BorrowedService borrowedService;
    private final Mapper mapper;

    @Operation(
            summary = "Get a list of borrowed books by member uuid",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Borrowed books retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BorrowedReadOnlyDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Member not found",
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
    @GetMapping("/member/{uuid}")
    public ResponseEntity<List<BorrowedReadOnlyDTO>> getBorrowedByMemberUuid(@PathVariable String uuid)
            throws EntityNotFoundException {
        return ResponseEntity.ok(borrowedService.getBorrowedByMemberUuid(uuid));
    }

    @Operation(
            summary = "Get a list of borrowed books by book isbn",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Borrowed books retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BorrowedReadOnlyDTO.class))
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
    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<BorrowedReadOnlyDTO>> getBorrowedByBookIsbn(@PathVariable String isbn)
            throws EntityNotFoundException {
        return ResponseEntity.ok(borrowedService.getBorrowedByBookIsbn(isbn));
    }

    @Operation(
            summary = "Borrow a book",
            responses = {
                    @ApiResponse(
                            responseCode = "201", description = "Book borrowed successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BorrowedReadOnlyDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "No available copies",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Member or book not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<BorrowedReadOnlyDTO> borrowBook(@Valid @RequestBody BorrowedInsertDTO dto)
            throws EntityNotFoundException, EntityInvalidArgumentException {
        Borrowed borrowed = borrowedService.borrowBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToBorrowedReadOnlyDTO(borrowed));
    }
}
