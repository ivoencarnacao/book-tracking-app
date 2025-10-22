CREATE TABLE IF NOT EXISTS book_tracker.user_books
(
  user_book_id BIGINT GENERATED ALWAYS AS IDENTITY,

  user_id BIGINT NOT NULL,
  book_publisher_id BIGINT NOT NULL,

  is_favorite BOOLEAN NOT NULL,
  is_wishlist BOOLEAN NOT NULL,
  is_bucketlist BOOLEAN NOT NULL,
  
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  
  CONSTRAINT pk_user_books PRIMARY KEY (user_book_id),
  CONSTRAINT fk_user_books_user FOREIGN KEY (user_id) 
    REFERENCES book_tracker.users(user_id) ON DELETE CASCADE,
  CONSTRAINT fk_user_books_book_publishers FOREIGN KEY (book_publisher_id) 
    REFERENCES book_tracker.book_publishers(book_publisher_id) ON DELETE CASCADE,

  CONSTRAINT uq_user_books_book_publishers UNIQUE (user_id, book_publisher_id)
);


