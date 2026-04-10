package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.dto.MemberInsertDTO;

public interface IMemberService {

    void saveMember(MemberInsertDTO memberInsertDTO) throws EntityAlreadyExistsException;
}
