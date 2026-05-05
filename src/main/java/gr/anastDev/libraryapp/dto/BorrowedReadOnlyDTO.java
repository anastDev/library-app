package gr.anastDev.libraryapp.dto;

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
    private String memberUuid;
    private String username;
    private String firstname;
    private String lastname;
    private String bookIsbn;
    private String bookTitle;
    private Instant borrowedAt;
    private Instant dueDate;
    private Instant returnedAt;
}
