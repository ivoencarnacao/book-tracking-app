CREATE TABLE IF NOT EXISTS book_tracker.publishers 
(
  publisher_id BIGINT GENERATED ALWAYS AS IDENTITY,

  publisher_name VARCHAR(255) NOT NULL,
  
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT pk_publishers PRIMARY KEY (publisher_id),

  CONSTRAINT uq_publishers_publisher_name UNIQUE (publisher_name)
);
