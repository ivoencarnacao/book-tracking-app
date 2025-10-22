package dev.ivoencarnacao.service.dto;

import java.util.List;

public record BookDetailDto(
        Long id,
        String title,
        List<AuthorDto> authors,
        PublisherDto publisher) {
}
