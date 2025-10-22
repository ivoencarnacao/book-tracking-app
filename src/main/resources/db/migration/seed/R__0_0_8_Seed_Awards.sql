INSERT INTO book_tracker.awards (award_name, award_category, awarded_by, award_announcement_date, is_active)
VALUES
  ('Nobel Prize in Literature', null, 'Svenska Akademien', '2025-10-09', TRUE)
ON CONFLICT (award_name) DO NOTHING;

