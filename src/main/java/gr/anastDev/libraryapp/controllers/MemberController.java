package gr.anastDev.libraryapp.controllers;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.core.exceptions.ValidationException;
import gr.anastDev.libraryapp.dto.*;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final Mapper mapper;

    @Operation(
            summary = "Get all members",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Members retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MemberReadOnlyDTO.class))
                    ),
            }
    )
    @GetMapping
    public ResponseEntity<List<MemberReadOnlyDTO>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @Operation(
            summary = "Save a member",
            responses = {
                    @ApiResponse(
                            responseCode = "201", description = "Member created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MemberReadOnlyDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "409", description = "Member already exists",
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
    public ResponseEntity<MemberReadOnlyDTO> saveMember(@Valid @RequestBody MemberInsertDTO dto, BindingResult bindingResult) throws EntityAlreadyExistsException, EntityInvalidArgumentException {
        if(bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        MemberReadOnlyDTO memberReadOnlyDTO = memberService.saveMember(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(memberReadOnlyDTO.uuid())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(memberReadOnlyDTO);
    }

    @Operation(
            summary = "Delete a member",
            responses = {
                    @ApiResponse(
                            responseCode = "204", description = "Member deleted",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Member not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDTO.class))
                    ),
            }
    )
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteMember(@PathVariable String uuid) throws EntityNotFoundException {
        memberService.deleteMember(uuid);
        return ResponseEntity.noContent().build();
    }
}
