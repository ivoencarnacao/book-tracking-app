CREATE TABLE IF NOT EXISTS book_tracker.authors 
(
  author_id BIGINT GENERATED ALWAYS AS IDENTITY,

  author_name VARCHAR(255) NOT NULL,
  pen_name VARCHAR(255),
  date_of_birth DATE,
  place_of_birth VARCHAR(255),
  author_nationality VARCHAR(100),
  author_language VARCHAR(100),

  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT pk_authors PRIMARY KEY (author_id),

  CONSTRAINT uq_authors_author_name UNIQUE (author_name), 
  CONSTRAINT uq_authors_pen_name UNIQUE (pen_name)
);
