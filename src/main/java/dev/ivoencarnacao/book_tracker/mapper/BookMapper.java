package dev.ivoencarnacao.book_tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.ivoencarnacao.book_tracker.dto.BookDetailDto;
import dev.ivoencarnacao.book_tracker.entity.BookPublisher;

@Mapper(componentModel = "spring", uses = { AuthorMapper.class, PublisherMapper.class })
public interface BookMapper {

  @Mapping(source = "book.id", target = "id")
  @Mapping(source = "book.title", target = "title")
  @Mapping(source = "book.bookAuthors", target = "authors")
  @Mapping(source = "publisher", target = "publisher")
  BookDetailDto toDto(BookPublisher bookPublisher);

}
