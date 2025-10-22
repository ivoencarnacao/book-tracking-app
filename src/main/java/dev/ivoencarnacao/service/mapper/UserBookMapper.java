package dev.ivoencarnacao.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.ivoencarnacao.service.dto.UserBookDetailDto;

import dev.ivoencarnacao.service.entity.UserBook;

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
