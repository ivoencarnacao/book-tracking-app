INSERT INTO book_tracker.users (username, email, password_hash)
VALUES
  ('user1', 'user1@nomail.pt', '$2a$12$pUoku.sU0fgYPSjWGblhBuT87i2/qkTIgeVEED1NeZ7ThKHYSSd/e'),
  ('user2', 'user2@nomail.pt', '$2a$12$n1Pk5RS/5AVpdMWNVNEDp.TYAfqkpu85ow537tIbk6r/2fzmEfuai'),
  ('guest', 'guest@nomail.pt', '$2a$12$twWFLrPwGWJXbDBqZGZRk.HKAmwmaBYUyR7VU8K/3TNN1cvjs9z4u')
ON CONFLICT (username) DO NOTHING;

