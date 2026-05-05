package gr.anastDev.libraryapp.mapper;

import gr.anastDev.libraryapp.dto.*;
import gr.anastDev.libraryapp.model.Book;
import gr.anastDev.libraryapp.model.Borrowed;
import gr.anastDev.libraryapp.model.Member;
import gr.anastDev.libraryapp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final PasswordEncoder passwordEncoder;

    public Book mapToBookEntity(BookInsertDTO dto) {
       Book book = new Book();
       book.setIsbn(dto.getIsbn());
       book.setThumbnail(dto.getThumbnail());
       book.setSmallThumbnail(dto.getSmallThumbnail());
       book.setTitle(dto.getTitle());
       book.setAuthor(dto.getAuthor());
       book.setGenre(dto.getGenre());
       book.setDescription(dto.getDescription());
       book.setYearOfPublish(dto.getYearOfPublish());
       book.setNumberOfPages(dto.getNumberOfPages());
       book.setAvailableCopies(dto.getAvailableCopies());

       return book;
    }

    public BookReadOnlyDTO mapToBookReadOnlyDTO (Book book) {
        return new BookReadOnlyDTO(
                book.getId(),
                book.getIsbn(),
                book.getThumbnail(),
                book.getSmallThumbnail(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getDescription(),
                book.getYearOfPublish(),
                book.getNumberOfPages(),
                book.getAvailableCopies(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

    public Member mapToMemberEntity(MemberInsertDTO dto) {
        UserInsertDTO userDTO = dto.userInsertDTO();

        User user = new User();
        user.setFirstname(userDTO.firstname());
        user.setLastname(userDTO.lastname());
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setDateOfBirth(userDTO.dateOfBirth());
        user.setGender(userDTO.gender());
        user.setRole(userDTO.role());

        Member member = new Member();
        member.setUser(user);
        return member;
    }

    public Member mapToMemberEntity(MemberUpdateDTO dto) {
        Member member = new Member();

        UserUpdateDTO userDTO = dto.userUpdateDTO();
        User user = new User();
        user.setFirstname(userDTO.firstname());
        user.setLastname(userDTO.lastname());
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setDateOfBirth(userDTO.dateOfBirth());
        user.setGender(userDTO.gender());
        user.setRole(userDTO.role());
        member.setUser(user);

        return member;
    }

    public MemberReadOnlyDTO mapToMemberReadOnlyDTO(Member member) {
       UserReadOnlyDTO userReadOnlyDTO = new UserReadOnlyDTO(member.getUser().getFirstname(), member.getUser().getLastname());

       return new MemberReadOnlyDTO(member.getId(), member.getUuid(), userReadOnlyDTO);
    }

    public BorrowedReadOnlyDTO mapToBorrowedReadOnlyDTO(Borrowed borrowed) {
        return new BorrowedReadOnlyDTO(
                borrowed.getId(),
                borrowed.getMember().getUuid(),
                borrowed.getMember().getUser().getUsername(),
                borrowed.getMember().getUser().getFirstname(),
                borrowed.getMember().getUser().getLastname(),
                borrowed.getBook().getIsbn(),
                borrowed.getBook().getTitle(),
                borrowed.getBorrowedAt(),
                borrowed.getDueDate(),
                borrowed.getReturnedAt()
        );
    }
}
