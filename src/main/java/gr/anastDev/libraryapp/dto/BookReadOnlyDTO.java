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
public class BookReadOnlyDTO {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String description;
    private Integer yearOfPublish;
    private Integer numberOfPages;
    private Integer availableCopies;
    private Instant createdAt;
    private Instant updatedAt;
}
