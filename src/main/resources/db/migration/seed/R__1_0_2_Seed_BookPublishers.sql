INSERT INTO book_tracker.book_publishers (book_id, publisher_id, publication_date)
SELECT 
  b.book_id, 
  p.publisher_id, 
  v.publication_date::date
FROM (
  VALUES
    -- https://www.bertrand.pt/livro/a-paz-das-colmeias-alice-rivaz/31864398
    ('A Paz das Colmeias', 'Antígona', '2025-04-01'),

    ('Bíblia do Vinho - O Guia Definitivo', 'Familiam', '2024-10-01'),

    -- https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164
    ('Clean Architecture: A Craftsmans Guide to Software Structure and Design', 'Pearson', '2017-09-10'),

    -- https://www.amazon.co.uk/Dead-Lions-Slough-House-Thriller/dp/1399805436
    ('Dead Lions', 'Baskerville', '2022-12-01'), -- Paperback

    --'https://www.bertrand.pt/livro/guia-popular-de-vinhos-2026-anibal-jose-coutinho/32479149'
    ('Guia Michelin Portugal 2024', 'Michelin', '2024-06-01'),
    ('Guia Michelin Portugal 2025', 'Michelin', '2025-06-01'),
    ('Guia Popular de Vinhos 2026', 'Editorial Presença', '2025-09-01'),

    ('Herscht 07769', 'Cavalo de Ferro', '2025-10-01'),

    ('Introdução ao Liberalismo', 'Dom Quixote', '2025-09-01'),
    
    ('Lamento de Uma América em Ruínas', 'Dom Quixote', '2017-07-01'),
    ('Learn Three.js', 'Packt Publishing', '2020-01-01'),
    ('Licença para Espiar', 'Casa das Letras', '2025-07-01'),
    ('London Rules', 'Baskerville', '2022-03-03'),

    ('O Clube de Leitura da CIA', 'Casa das Letras', '2025-10-01'),
    
    -- https://www.bertrand.pt/livro/o-dever-de-deslumbrar-filipa-martins/21332326
    ('O Dever de Deslumbrar: Biografia de Natália Correia', 'Contraponto Editores', '2023-03-01'),

    ('Modern Frontends with HTMX', 'Packt Publishing', '2021-09-01'),
    
    ('O Enigma de Israel: Uma História do Estado de Israel', 'Dom Quixote', '2025-09-01'),
    ('O Mundo Em Que Vivi', 'Edições Afrontamento', '1900-01-01'),
    ('O Mundo Em Que Vivi', 'Porto Editora', '1901-01-01'),
    ('Oreo', 'Antígona', '1902-01-01'),

    ('Por Dentro do Chega: A face oculta da extrema-direita em Portugal', 'Objectiva', '2025-09-01'),

    -- https://www.amazon.co.uk/Real-Tigers-Slough-House-Thriller/dp/1399803298
    ('Real Tigers', 'Baskerville', '2022-03-03'), -- Paperback

    ('Taming Thymeleaf', 'Packt Publishing', '2020-06-01'),

    -- https://www.amazon.co.uk/Terminal-List-MAJOR-Amazon-starring/dp/1398554782
    ('The Terminal List: A Thriller', 'Simon & Schuster UK', '2025-09-11'), -- Paperback

    -- https://www.amazon.co.uk/Slow-Horses-Anniversary-Britains-greatest/dp/1399825976
    ('Slow Horses', 'Baskerville', '2025-06-05'), -- Hardcover

    --https://www.amazon.co.uk/Slow-Horses-Slough-House-Thriller/dp/152939404X
    ('Slow Horses', 'Baskerville', '2022-03-31'), -- Paperback
    
    -- https://www.wook.pt/livro/covil-de-espioes-mick-herron/24736046
    ('Slow Horses', 'Marcador', '2021-05-01'),

    -- https://www.amazon.co.uk/Spook-Street-Slough-House-Thriller/dp/1399803077
    ('Spook Street', 'Baskerville', '2022-03-03'), -- Paperback

    -- https://www.manning.com/books/spring-start-here
    ('Spring Start Here', 'Manning', '2021-09-01'),

    -- https://www.amazon.co.uk/World-Atlas-Wine-Hugh-Johnson/dp/1784724033
    ('The World Atlas of Wine', 'Mitchell Beazley', '2019-10-03')
) AS v(book_title, publisher_name, publication_date)
JOIN book_tracker.books b ON b.book_title = v.book_title
JOIN book_tracker.publishers p ON p.publisher_name = v.publisher_name
ON CONFLICT (book_id, publisher_id, publication_date) DO NOTHING;
