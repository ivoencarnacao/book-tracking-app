CREATE TABLE IF NOT EXISTS book_tracker.users 
(
  user_id BIGINT GENERATED ALWAYS AS IDENTITY,

  username VARCHAR(50) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password_hash TEXT NOT NULL, 
  
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE,

  CONSTRAINT pk_users PRIMARY KEY (user_id),
  
  CONSTRAINT uq_users_username UNIQUE (username),
  CONSTRAINT uq_users_email UNIQUE (email)
);
