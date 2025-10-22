CREATE TABLE IF NOT EXISTS book_tracker.books 
(
  book_id BIGINT GENERATED ALWAYS AS IDENTITY,

  book_title VARCHAR(255) NOT NULL,

  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT pk_books PRIMARY KEY (book_id),
  
  CONSTRAINT uq_books_book_title UNIQUE (book_title)    
);