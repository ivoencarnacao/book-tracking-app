INSERT INTO book_tracker.author_awards (author_id, award_id, award_year)
SELECT 
  a.author_id, 
  aw.award_id,
  v.award_year
FROM (
  VALUES
    ('László Krasznahorkai', 'Nobel Prize in Literature', 2025)
) AS v(author_name, award_name, award_year)
JOIN book_tracker.authors a ON a.author_name = v.author_name
JOIN book_tracker.awards aw ON aw.award_name = v.award_name
ON CONFLICT (author_id, award_id, award_year) DO NOTHING;