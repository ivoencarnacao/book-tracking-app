INSERT INTO book_tracker.authors (author_name, pen_name, date_of_birth, place_of_birth, author_nationality, author_language)
VALUES 
  ('Alice Rivaz', null, null, null, null, null),
  ('Aníbal José Coutinho', null, null, null, 'Portuguese', 'Portuguese'),
  ('Charlie English', null, null, null, 'British', 'English'),
  ('Carmen de Posadas Mañé', 'Carmen Posadas', '1953-08-13', 'Montevideo, Uruguay', 'Uruguayan-Spanish', 'Spanish'),
  ('Filipa Martins', null, null, null, null, null),
  -- https://en.wikipedia.org/wiki/Fran_Ross
  ('Fran Ross', null, '1935-06-25', 'Philadelphia, Pennsylvania, USA', 'American', null),
  -- https://en.wikipedia.org/wiki/Ilse_Losa
  ('Henrique Cymerman', null, '1959-03-23', 'Porto, Germany', 'Portuguese', null),
  ('Ilse Losa', null,  '1913-03-20', 'Melle, Germany', 'Portuguese', null),
  ('Jack Carr', null, null, null, null, null),
  ('James David Vance', 'JD Vance', '1984-08-02', 'Middletown, Ohio, US', 'American', 'English'),
  ('Jancis Robinson', null, null, null, 'British', null),
  ('Jos Dirksen', null, null, null, null, null),
  ('Hugh Johnson', null, null, null, 'British', null),
  ('László Krasznahorkai', null, '1954-01-05', 'Gyula, Hungary', 'Hungarian', 'Hungarian'),
  ('Laurentiu Spilca', null, null, null, null, null),
  ('Madeline Puckette', null, null, null, null, null),
  ('Michelin', null, null, null, null, null),
  ('Mick Herron', null, null, null, 'British', null),
  ('Miguel Ferreira Morgado', 'Miguel Morgado', '1974-07-18', 'Setúbal, Portugal', 'Portuguese', 'Portuguese'),
  ('Miguel Carvalho', null, null, null, 'Portuguese', 'Portuguese'),
  ('Neil Pendock', null, null, null, null, null),
  ('Robert C. Martin', null, null, null, null, null),
  ('Wim Deblauwe', null, null, null, null, null)
ON CONFLICT (author_name) DO NOTHING;

