package dev.ivoencarnacao.service.dto;

public record UserBookDetailDto(
                Long userBookId,
                String username,
                BookDetailDto book,
                boolean isFavorite,
                boolean isWishlist,
                boolean isBucketlist) {

}
