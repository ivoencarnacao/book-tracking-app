package dev.ivoencarnacao.book_tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.ivoencarnacao.book_tracker.dto.PublisherDto;
import dev.ivoencarnacao.book_tracker.entity.BookPublisher;
import dev.ivoencarnacao.book_tracker.entity.Publisher;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  PublisherDto toDto(Publisher publisher);

  default PublisherDto toDto(BookPublisher bookPublisher) {
    if (bookPublisher == null || bookPublisher.getPublisher() == null) {
      return null;
    }
    return toDto(bookPublisher.getPublisher());
  }
}
