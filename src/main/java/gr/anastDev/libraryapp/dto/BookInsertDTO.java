package gr.anastDev.libraryapp.dto;

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

    @NotBlank
    @Size(min = 1, max = 200)
    private String title;

    @NotBlank
    @Size(min = 1, max = 100)
    private String author;

    @NotBlank
    @Size(min = 1, max = 50)
    private String genre;

    @NotBlank
    @Size(max = 2000)
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
