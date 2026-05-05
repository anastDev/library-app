package gr.anastDev.libraryapp.dto;

import lombok.Builder;

@Builder
public record UserReadOnlyDTO(String firstname, String lastname){}