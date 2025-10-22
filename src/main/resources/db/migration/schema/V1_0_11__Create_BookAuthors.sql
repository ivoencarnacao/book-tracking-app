CREATE TABLE IF NOT EXISTS book_tracker.book_authors
(
  book_author_id BIGINT GENERATED ALWAYS AS IDENTITY,

  book_id BIGINT NOT NULL,
  author_id BIGINT NOT NULL,

  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT pk_book_authors PRIMARY KEY (book_author_id),
  CONSTRAINT fk_book_authors_book FOREIGN KEY (book_id) 
    REFERENCES book_tracker.books(book_id) ON DELETE CASCADE,
  CONSTRAINT fk_book_authors_author FOREIGN KEY (author_id) 
    REFERENCES book_tracker.authors(author_id) ON DELETE CASCADE,

  CONSTRAINT uq_book_authors UNIQUE (book_id, author_id)
);