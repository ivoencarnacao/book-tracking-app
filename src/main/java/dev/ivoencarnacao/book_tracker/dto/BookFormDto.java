package dev.ivoencarnacao.book_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookFormDto(

        @NotBlank(message = "{form.book.title.notBlank}") @Size(min = 2, max = 255, message = "{form.book.title.size}") String title,

        @NotBlank(message = "{form.book.authorName.notBlank}") String authorName,

        @NotBlank(message = "{form.book.publisherName.notBlank}") String publisherName,

        String publicationDate) {

}
