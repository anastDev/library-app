package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.dto.MemberInsertDTO;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements IMemberService {
    private final MemberRepository memberRepository;
    private final Mapper mapper;

    @Override
    public void saveMember(MemberInsertDTO memberInsertDTO) throws EntityAlreadyExistsException {

    }
}
