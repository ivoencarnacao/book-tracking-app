INSERT INTO book_tracker.book_authors (book_id, author_id)
SELECT b.book_id, a.author_id
FROM (VALUES
  ('A Paz das Colmeias', 'Alice Rivaz'),
  ('Bíblia do Vinho - O Guia Definitivo', 'Madeline Puckette'),
  ('Clean Architecture: A Craftsmans Guide to Software Structure and Design', 'Robert C. Martin'),
  ('Dead Lions', 'Mick Herron'),
  ('Guia Popular de Vinhos 2026', 'Aníbal José Coutinho'),
  ('Guia Popular de Vinhos 2026', 'Neil Pendock'),
  ('Herscht 07769', 'László Krasznahorkai'),
  ('Introdução ao Liberalismo', 'Miguel Ferreira Morgado'),
  ('Lamento de Uma América em Ruínas', 'James David Vance'),
  ('Learn Three.js', 'Jos Dirksen'),
  ('Licença para Espiar', 'Carmen de Posadas Mañé'),
  ('London Rules', 'Mick Herron'),
  ('Modern Frontends with HTMX', 'Wim Deblauwe'),
  ('O Clube de Leitura da CIA', 'Charlie English'),
  ('O Dever de Deslumbrar: Biografia de Natália Correia', 'Filipa Martins'),
  ('O Enigma de Israel: Uma História do Estado de Israel', 'Henrique Cymerman'),
  ('O Mundo Em Que Vivi', 'Ilse Losa'),
  ('Oreo', 'Fran Ross'),
  ('Por Dentro do Chega: A face oculta da extrema-direita em Portugal', 'Miguel Carvalho'),
  ('Taming Thymeleaf', 'Wim Deblauwe'),  
  ('The World Atlas of Wine', 'Jancis Robinson'),
  ('The World Atlas of Wine', 'Hugh Johnson'),
  ('The Terminal List: A Thriller', 'Jack Carr'),
  ('Real Tigers', 'Mick Herron'),
  ('Slow Horses', 'Mick Herron'),
  ('Spook Street', 'Mick Herron'),
  ('Spring Start Here', 'Laurentiu Spilca')
) AS v(book_title, author_name)
JOIN book_tracker.books b ON b.book_title = v.book_title
JOIN book_tracker.authors a ON a.author_name = v.author_name
ON CONFLICT (book_id, author_id) DO NOTHING;