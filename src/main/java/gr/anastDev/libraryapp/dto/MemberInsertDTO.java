package gr.anastDev.libraryapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MemberInsertDTO (
        @Valid
        @NotNull(message = "User details are required")
        UserInsertDTO userInsertDTO
) {}
