package dev.ivoencarnacao.book_tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.ivoencarnacao.book_tracker.dto.UserBookDetailDto;

import dev.ivoencarnacao.book_tracker.entity.UserBook;

@Mapper(componentModel = "spring", uses = { BookMapper.class })
public interface UserBookMapper {

  @Mapping(source = "userBookId", target = "userBookId")
  @Mapping(source = "user.username", target = "username")
  @Mapping(source = "bookPublisher", target = "book")
  @Mapping(source = "favorite", target = "isFavorite")
  @Mapping(source = "wishlist", target = "isWishlist")
  @Mapping(source = "bucketlist", target = "isBucketlist")
  UserBookDetailDto toDto(UserBook userBook);

}
