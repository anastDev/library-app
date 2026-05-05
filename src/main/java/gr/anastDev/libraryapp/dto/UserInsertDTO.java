package gr.anastDev.libraryapp.dto;

import gr.anastDev.libraryapp.core.enums.GenderType;
import gr.anastDev.libraryapp.core.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserInsertDTO (
        @NotBlank(message = "Firstname is required")
        String firstname,

    @NotEmpty(message = "Lastname is required")
    String lastname,

    @NotBlank(message = "Username is required")
    String username,

        @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email")
        String email,

    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@#$!%&*]).{8,}$",
        message = "Invalid Password")
    String password,

    @NotNull(message = "Date of birth is required")
        LocalDate dateOfBirth,

    @NotNull(message = "Gender is required")
        GenderType gender,

    @NotNull(message = "Role is required")
        Role role
) {}
