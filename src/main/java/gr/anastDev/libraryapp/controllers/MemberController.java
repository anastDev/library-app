package gr.anastDev.libraryapp.controllers;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.*;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Member;
import gr.anastDev.libraryapp.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<List<MemberReadOnlyDTO>> getAllBooks() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }


    @PostMapping
    public ResponseEntity<MemberReadOnlyDTO> createMember(@Valid @RequestBody MemberInsertDTO dto) throws EntityAlreadyExistsException, EntityInvalidArgumentException {
        Member member = memberService.saveMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToMemberReadOnlyDTO(member));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteMember(@PathVariable String uuid) throws EntityNotFoundException {
        memberService.deleteMember(uuid);
        return ResponseEntity.noContent().build();
    }
}
