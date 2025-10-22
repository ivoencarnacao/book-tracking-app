CREATE TABLE IF NOT EXISTS book_tracker.author_awards
(
  author_award_id BIGINT GENERATED ALWAYS AS IDENTITY,

  author_id BIGINT NOT NULL,
  award_id BIGINT NOT NULL,
  
  award_year SMALLINT,

  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE,
  
  CONSTRAINT pk_author_awards PRIMARY KEY (author_award_id),
  CONSTRAINT fk_author_awards_author FOREIGN KEY (author_id) 
    REFERENCES book_tracker.authors(author_id) ON DELETE CASCADE,
  CONSTRAINT fk_author_awards_award FOREIGN KEY (award_id) 
    REFERENCES book_tracker.awards(award_id) ON DELETE CASCADE,
  
  CONSTRAINT uq_author_awards UNIQUE (author_id, award_id, award_year)
);