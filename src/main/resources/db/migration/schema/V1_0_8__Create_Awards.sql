CREATE TABLE IF NOT EXISTS book_tracker.awards 
(
  award_id BIGINT GENERATED ALWAYS AS IDENTITY,

  award_name VARCHAR(255) NOT NULL,
  award_category VARCHAR(100),
  awarded_by VARCHAR(255),
  award_announcement_date DATE,
  is_active BOOLEAN,

  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE,  

  CONSTRAINT pk_awards PRIMARY KEY (award_id),

  CONSTRAINT uq_awards_award_name UNIQUE (award_name)
);
