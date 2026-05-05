package gr.anastDev.libraryapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MemberUpdateDTO ( @NotNull(message = "id field is required")
                               Long id,

                               @NotNull(message = "uuid field is required")
                               String uuid,

                               @Valid
                                @NotNull(message = "User details are required")
                                UserUpdateDTO userUpdateDTO
                               ) {

}