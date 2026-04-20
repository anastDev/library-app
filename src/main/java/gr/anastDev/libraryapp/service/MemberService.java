package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.MemberInsertDTO;
import gr.anastDev.libraryapp.dto.MemberReadOnlyDTO;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Member;
import gr.anastDev.libraryapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements IMemberService {

    private final MemberRepository memberRepository;
    private final Mapper mapper;

    @Override
    public List<MemberReadOnlyDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(mapper::mapToMemberReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Member saveMember(MemberInsertDTO dto) throws EntityAlreadyExistsException {
        try {
            if (memberRepository.existsByUsername(dto.getUsername())) {
                throw new EntityAlreadyExistsException("Member", "Member with username " + dto.getUsername() + "already exists!");
            }

            if(memberRepository.existsByEmail(dto.getEmail())) {
                throw new EntityAlreadyExistsException("Member", "Member with email " + dto.getEmail() + "already exists!");
            }

            Member member = mapper.mapToMemberEntity(dto);
            memberRepository.save(member);
            log.info("Member with username={} saved!", dto.getUsername());
            return member;
        } catch(EntityAlreadyExistsException e) {
            log.error("Member with username={} already exists!", dto.getUsername(), e);
            throw e;
        }
    }

    @Override
    public void deleteMember(String uuid) throws EntityNotFoundException {
        try {
            Member member = memberRepository.findByUuid(uuid)
                    .orElseThrow(() -> new EntityNotFoundException("Member", "Member with id " + uuid + "not found!"));

            memberRepository.deleteById(member.getId());
            log.info("Book with uuid={} deleted!", uuid);
        } catch (EntityNotFoundException e) {
            log.error("Member with uuid={} not found!", uuid, e);
            throw e;
        }
    }
}
