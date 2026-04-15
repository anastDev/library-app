package gr.anastDev.libraryapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberInsertDTO {

    @NotNull(message = "Firstname field should not be empty.")
    @Size(min = 2)
    private String firstname;

    @Size(min = 2)
    private String lastname;

    @NotNull(message = "Username field should not be empty.")
    @Size(min = 3, max = 20, message = "Username should be between 2 - 20 characters long.")
    private String username;

    @NotNull(message = "Password field should not be null")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])^.{8,}$",
            message = "The password must contain at least 1 lowercase letter, 1 uppercase letter, 1 digit and 1 special character with no spaces.")
    private String password;

    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
}
