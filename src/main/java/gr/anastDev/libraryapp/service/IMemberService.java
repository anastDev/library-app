package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.MemberInsertDTO;
import gr.anastDev.libraryapp.dto.MemberReadOnlyDTO;
import gr.anastDev.libraryapp.dto.MemberUpdateDTO;
import gr.anastDev.libraryapp.model.Member;

import java.util.List;

public interface IMemberService {

    List<MemberReadOnlyDTO> getAllMembers();
    MemberReadOnlyDTO saveMember(MemberInsertDTO memberInsertDTO) throws EntityAlreadyExistsException;
    MemberReadOnlyDTO updateMember(MemberUpdateDTO memberUpdateDTO) throws EntityAlreadyExistsException, EntityNotFoundException;
    void deleteMember(String uuid) throws EntityNotFoundException;
}
