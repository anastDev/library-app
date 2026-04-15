package gr.anastDev.libraryapp.mapper;

import gr.anastDev.libraryapp.dto.BookInsertDTO;
import gr.anastDev.libraryapp.dto.BookReadOnlyDTO;
import gr.anastDev.libraryapp.dto.BorrowedReadOnlyDTO;
import gr.anastDev.libraryapp.dto.MemberReadOnlyDTO;
import gr.anastDev.libraryapp.model.Book;
import gr.anastDev.libraryapp.model.Borrowed;
import gr.anastDev.libraryapp.model.Member;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class Mapper {

    public Book mapToBookEntity(BookInsertDTO dto) {
        return new Book(
                null,
                dto.getIsbn(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getGenre(),
                dto.getDescription(),
                dto.getYearOfPublish(),
                dto.getNumberOfPages(),
                dto.getAvailableCopies(),
                new HashSet<>()
        );
    }

    public BookReadOnlyDTO mapToBookReadOnlyDTO (Book book) {
        return new BookReadOnlyDTO(
                book.getId(),
                book.getIsbn(),
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

    public MemberReadOnlyDTO mapToMemberReadOnlyDTO(Member member) {
        return new MemberReadOnlyDTO(
                member.getId(),
                member.getFirstname(),
                member.getLastname(),
                member.getUsername(),
                member.getEmail(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    public BorrowedReadOnlyDTO mapToBorrowedReadOnlyDTO(Borrowed borrowed) {
        return new BorrowedReadOnlyDTO(
                borrowed.getId(),
                borrowed.getMember(),
                borrowed.getBook(),
                borrowed.getBorrowedAt(),
                borrowed.getDueDate(),
                borrowed.getReturnedAt()
        );
    }
}
