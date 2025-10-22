CREATE TABLE IF NOT EXISTS book_tracker.book_publishers
(
  book_publisher_id BIGINT GENERATED ALWAYS AS IDENTITY,

  book_id BIGINT NOT NULL,
  publisher_id BIGINT NOT NULL,

  publication_date DATE,

  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT pk_book_publishers PRIMARY KEY (book_publisher_id),
  CONSTRAINT fk_book_publishers_book FOREIGN KEY (book_id) 
    REFERENCES book_tracker.books(book_id) ON DELETE CASCADE,
  CONSTRAINT fk_book_publishers_publisher FOREIGN KEY (publisher_id) 
    REFERENCES book_tracker.publishers(publisher_id) ON DELETE CASCADE,

  CONSTRAINT uq_book_publishers_publication_date UNIQUE (book_id, publisher_id, publication_date)
);