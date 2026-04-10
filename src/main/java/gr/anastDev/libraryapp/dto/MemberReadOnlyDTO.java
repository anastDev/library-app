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
public class MemberReadOnlyDTO {

    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
}
