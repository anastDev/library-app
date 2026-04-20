package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.MemberInsertDTO;
import gr.anastDev.libraryapp.dto.MemberReadOnlyDTO;
import gr.anastDev.libraryapp.model.Member;

import java.util.List;

public interface IMemberService {

    List<MemberReadOnlyDTO> getAllMembers();
    Member saveMember(MemberInsertDTO memberInsertDTO) throws EntityAlreadyExistsException;
    void deleteMember(String uuid) throws EntityNotFoundException;
}
