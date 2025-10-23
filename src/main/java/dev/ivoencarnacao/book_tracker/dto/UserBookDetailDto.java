package dev.ivoencarnacao.book_tracker.dto;

public record UserBookDetailDto(
                Long userBookId,
                String username,
                BookDetailDto book,
                boolean isFavorite,
                boolean isWishlist,
                boolean isBucketlist) {

}
