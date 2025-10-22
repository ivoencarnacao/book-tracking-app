package dev.ivoencarnacao.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.ivoencarnacao.service.dto.AuthorDto;
import dev.ivoencarnacao.service.entity.Author;
import dev.ivoencarnacao.service.entity.BookAuthor;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  AuthorDto toDto(Author author);

  default AuthorDto toDto(BookAuthor bookAuthor) {
    if (bookAuthor == null || bookAuthor.getAuthor() == null) {
      return null;
    }
    return toDto(bookAuthor.getAuthor());
  }
}
