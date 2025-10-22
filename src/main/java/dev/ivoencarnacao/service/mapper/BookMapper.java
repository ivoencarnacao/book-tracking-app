package dev.ivoencarnacao.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.ivoencarnacao.service.dto.BookDetailDto;
import dev.ivoencarnacao.service.entity.BookPublisher;

@Mapper(componentModel = "spring", uses = { AuthorMapper.class, PublisherMapper.class })
public interface BookMapper {

  @Mapping(source = "book.id", target = "id")
  @Mapping(source = "book.title", target = "title")
  @Mapping(source = "book.bookAuthors", target = "authors")
  @Mapping(source = "publisher", target = "publisher")
  BookDetailDto toDto(BookPublisher bookPublisher);

}
