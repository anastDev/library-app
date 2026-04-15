package gr.anastDev.libraryapp.dto;

import gr.anastDev.libraryapp.model.Book;
import gr.anastDev.libraryapp.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BorrowedReadOnlyDTO {

    private Long id;
    private Member member;
    private Book book;
    private Instant borrowedAt;
    private Instant dueDate;
    private Instant returnedAt;
}
