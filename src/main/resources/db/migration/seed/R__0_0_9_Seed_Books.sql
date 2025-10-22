INSERT INTO book_tracker.books (book_title)
VALUES
  -- https://www.bertrand.pt/livro/a-paz-das-colmeias-alice-rivaz/31864398
  ('A Paz das Colmeias'),

  ('Bíblia do Vinho - O Guia Definitivo'),

  ('Clean Architecture: A Craftsmans Guide to Software Structure and Design'),

  -- https://www.amazon.co.uk/Dead-Lions-Slough-House-Thriller/dp/1399805436
  ('Dead Lions'), -- Paperback

  --'https://www.bertrand.pt/livro/guia-popular-de-vinhos-2026-anibal-jose-coutinho/32479149'
  ('Guia Popular de Vinhos 2026'),

  -- https://penguinlivros.pt/loja/cavalo-de-ferro/livro/herscht-07769/
  ('Herscht 07769'),

  ('Introdução ao Liberalismo'),

  ('Lamento de Uma América em Ruínas'),
  ('Learn Three.js'),
  ('Licença para Espiar'),

  -- https://www.amazon.co.uk/London-Rules-Slough-House-Thriller/dp/1399803085
  ('London Rules'), -- Paperback

  ('Modern Frontends with HTMX'),

  ('O Clube de Leitura da CIA'),
  
  -- https://www.bertrand.pt/livro/o-dever-de-deslumbrar-filipa-martins/21332326
  ('O Dever de Deslumbrar: Biografia de Natália Correia'),

  -- https://www.bertrand.pt/livro/o-enigma-de-israel-henrique-cymerman/32486772
  ('O Enigma de Israel: Uma História do Estado de Israel'),
  ('O Mundo Em Que Vivi'),
  ('Oreo'),
  
  ('Por Dentro do Chega: A face oculta da extrema-direita em Portugal'),

  -- https://www.amazon.co.uk/Real-Tigers-Slough-House-Thriller/dp/1399803298
  ('Real Tigers'), -- Paperback

  ('Spring Start Here'),
  ('Slow Horses'),
  ('Spook Street'),

  ('Taming Thymeleaf'),
  ('The Terminal List: A Thriller'),
  ('The World Atlas of Wine')
ON CONFLICT (book_title) DO NOTHING;
