package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityAlreadyExistsException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.MemberInsertDTO;
import gr.anastDev.libraryapp.dto.MemberReadOnlyDTO;
import gr.anastDev.libraryapp.dto.MemberUpdateDTO;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Member;
import gr.anastDev.libraryapp.repository.MemberRepository;
import gr.anastDev.libraryapp.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public List<MemberReadOnlyDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(mapper::mapToMemberReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public MemberReadOnlyDTO saveMember(MemberInsertDTO dto) throws EntityAlreadyExistsException {
        try {
            if (userRepository.findByUsername(dto.userInsertDTO().username()).isPresent()) {
                throw new EntityAlreadyExistsException("Member", "Member with username " + dto.userInsertDTO().username() + " already exists!");
            }

            if(userRepository.findByUsername(dto.userInsertDTO().email()).isPresent()) {
                throw new EntityAlreadyExistsException("Member", "Member with email " + dto.userInsertDTO().email() + " already exists!");
            }

            Member member = mapper.mapToMemberEntity(dto);
            Member savedMember = memberRepository.save(member);
            log.info("Member with username={} saved!", dto.userInsertDTO().username());
            return mapper.mapToMemberReadOnlyDTO(savedMember);
        } catch(EntityAlreadyExistsException e) {
            log.error("Member with username={} already exists!", dto.userInsertDTO().username(), e);
            throw e;
        }
    }

    @Override
    public MemberReadOnlyDTO updateMember(MemberUpdateDTO memberUpdateDTO) throws EntityAlreadyExistsException, EntityNotFoundException{
        Member existingMember = memberRepository.findById(memberUpdateDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("Member", "Member with id=" + memberUpdateDTO.id() + " not found!"));

        if(!existingMember.getUuid().equals(memberUpdateDTO.uuid()) && memberRepository.findByUuid(memberUpdateDTO.uuid()).isPresent()) {
            throw new EntityAlreadyExistsException("Member", "Member with uuid " + memberUpdateDTO.uuid() + " already exists.");
        }

        Member memberToUpdate = mapper.mapToMemberEntity(memberUpdateDTO);
        Member updatedMember = memberRepository.save(memberToUpdate);
        log.info("Member with uuid={} saved.", memberUpdateDTO.uuid());
        return mapper.mapToMemberReadOnlyDTO(updatedMember);
    }

    @Override
    public void deleteMember(String uuid) throws EntityNotFoundException {
        try {
            Member member = memberRepository.findByUuid(uuid)
                    .orElseThrow(() -> new EntityNotFoundException("Member", "Member with id " + uuid + " not found!"));

            memberRepository.deleteById(member.getId());
            log.info("Member with uuid={} deleted!", uuid);
        } catch (EntityNotFoundException e) {
            log.error("Member with uuid={} not found!", uuid, e);
            throw e;
        }
    }
}
