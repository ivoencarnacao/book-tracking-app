package dev.ivoencarnacao.book_tracker.dto;

import java.util.List;

public record BookDetailDto(
        Long id,
        String title,
        List<AuthorDto> authors,
        PublisherDto publisher) {
}
