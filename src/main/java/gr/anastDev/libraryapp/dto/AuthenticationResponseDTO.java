package gr.anastDev.libraryapp.dto;

public record AuthenticationResponseDTO(
        String firstname,
        String lastname,
        String token
) {
}
