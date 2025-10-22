INSERT INTO book_tracker.publishers (publisher_name)
VALUES 
  ('Antígona'),
  ('Atria/Emily Bestler Books'),

  ('Baskerville'),

  ('Casa das Letras'),

  -- https://penguinlivros.pt/loja/cavalo-de-ferro/livro/herscht-07769/
  ('Cavalo de Ferro'), 
  ('Contraponto Editores'),
  
  ('Dom Quixote'),
  
  -- https://www.edicoesafrontamento.pt/
  ('Edições Afrontamento'),
  ('Editorial Presença'),

  ('Familiam'),

  ('Leanpub'),

  ('Marcador'),
  ('Manning'),
  
  -- O Mundo Em Que Vivi, 1949
  ('Marânus'),
  ('Michelin'),  
  ('Mitchell Beazley'),

  ('Objectiva'),
  
  ('Packt Publishing'),
  ('Pearson'),
  ('Penguin Random House'),
  
  -- https://www.portoeditora.pt/
  ('Porto Editora'),
  
  ('Simon & Schuster UK')
ON CONFLICT (publisher_name) DO NOTHING;
