package gr.anastDev.libraryapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookInsertDTO {

    @NotBlank
    @Size(min = 10, max = 17)
    private String isbn;

    private String thumbnail;
    private String smallThumbnail;

    @NotBlank
    @Size(min = 1, max = 200)
    private String title;

    @NotBlank
    @Size(min = 1, max = 100)
    private String author;

    @NotBlank
    @Size(min = 1, max = 50)
    private String genre;

    @Column(columnDefinition = "TEXT")
    @NotBlank
    private String description;

    @NotNull
    @Min(1000)
    @Max(2100)
    private Integer yearOfPublish;

    @NotNull
    @Min(1)
    private Integer numberOfPages;

    @NotNull
    @Min(0)
    private Integer availableCopies;
}
