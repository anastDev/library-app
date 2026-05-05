package gr.anastDev.libraryapp.dto;

import lombok.Builder;

@Builder
public record MemberReadOnlyDTO(
        Long id,
        String uuid,
        UserReadOnlyDTO userReadOnlyDTO
) {}