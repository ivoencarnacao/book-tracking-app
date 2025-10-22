INSERT INTO book_tracker.user_books (user_id, book_publisher_id, is_favorite, is_wishlist, is_bucketlist)
SELECT
    u.user_id,
    bp.book_publisher_id,
    v.is_favorite,
    v.is_wishlist,
    v.is_bucketlist
FROM (
    VALUES
        -- Username, Title, Publisher, publicationDate, is_favorite, is_wishlist, is_bucketlist
        ('user2', 'A Paz das Colmeias', 'Antígona', '2025-04-01'::date, FALSE, TRUE, TRUE),
        ('user2', 'Licença para Espiar', 'Casa das Letras', '2025-07-01'::date, FALSE, FALSE, TRUE),
        ('user2', 'O Clube de Leitura da CIA', 'Casa das Letras', '2025-10-01'::date, FALSE, TRUE, FALSE),
        ('user2', 'O Dever de Deslumbrar: Biografia de Natália Correia', 'Contraponto Editores', '2023-03-01'::date, FALSE, TRUE, TRUE),
        ('user2', 'O Enigma de Israel: Uma História do Estado de Israel', 'Dom Quixote', '2025-09-01'::date, FALSE, TRUE, TRUE),
        ('user2', 'O Mundo Em Que Vivi', 'Edições Afrontamento', '1900-01-01'::date, TRUE, FALSE, FALSE),
        ('user2', 'O Mundo Em Que Vivi', 'Porto Editora', '1901-01-01'::date, FALSE, FALSE, FALSE),
        ('user2', 'Oreo', 'Antígona', '1902-01-01'::date, TRUE, FALSE, FALSE),
        ('user1', 'Bíblia do Vinho - O Guia Definitivo', 'Familiam', '2024-10-01'::date, FALSE, TRUE, FALSE), -- Hardcover
        ('user1', 'Clean Architecture: A Craftsmans Guide to Software Structure and Design', 'Pearson', '2017-09-10'::date, FALSE, TRUE, FALSE),
        ('user1', 'Dead Lions', 'Baskerville', '2022-12-01'::date, TRUE, FALSE, FALSE), -- Paperback
        ('user1', 'Dead Lions', 'Baskerville', '2022-12-01'::date, FALSE, FALSE, TRUE),
        ('user1', 'Guia Michelin Portugal 2025', 'Michelin', '2025-06-01'::date, FALSE, TRUE, FALSE),
        ('user1', 'Guia Popular de Vinhos 2026', 'Editorial Presença', '2025-09-01'::date, FALSE, TRUE, TRUE),
        ('user1', 'Introdução ao Liberalismo', 'Dom Quixote', '2025-09-01'::date, FALSE, FALSE, TRUE),
        ('user1', 'Lamento de Uma América em Ruínas', 'Dom Quixote', '2017-07-01'::date, TRUE, TRUE, TRUE),
        ('user1', 'Learn Three.js', 'Packt Publishing', '2020-01-01'::date, FALSE, TRUE, FALSE),
        ('user1', 'London Rules', 'Baskerville', '2022-03-03'::date, FALSE, FALSE, TRUE),
        ('user1', 'Modern Frontends with HTMX', 'Packt Publishing', '2021-09-01'::date, FALSE, TRUE, TRUE),
        ('user1', 'O Enigma de Israel: Uma História do Estado Judaico', 'Dom Quixote', '2025-09-01'::date, FALSE, FALSE, TRUE),
        ('user1', 'Por Dentro do Chega: A face oculta da extrema-direita em Portugal', 'Objectiva', '2025-09-01'::date, FALSE, TRUE, TRUE),
        ('user1', 'Taming Thymeleaf', 'Packt Publishing', '2020-06-01'::date, FALSE, FALSE, FALSE),
        ('user1', 'The Terminal List: A Thriller', 'Simon & Schuster UK', '2025-09-11'::date, FALSE, FALSE, TRUE),
        ('user1', 'The World Atlas of Wine', 'Mitchell Beazley', '2019-10-03'::date, FALSE, TRUE, TRUE),
        ('user1', 'Real Tigers', 'Baskerville', '2022-03-03'::date, FALSE, FALSE, TRUE),
        ('user1', 'Slow Horses', 'Baskerville', '2025-06-05'::date, FALSE, TRUE, TRUE), -- Hardcover
        ('user1', 'Slow Horses', 'Baskerville', '2022-03-31'::date, FALSE, FALSE, TRUE), -- Paperback
        ('user1', 'Slow Horses', 'Marcador', '2021-05-01'::date, FALSE, TRUE, TRUE), -- Ed. pt-PT
        ('user1', 'Spook Street', 'Baskerville', '2022-03-03'::date, FALSE, FALSE, TRUE),
        ('user1', 'Spring Start Here', 'Manning', '2021-09-01'::date, FALSE, FALSE, TRUE)
) AS v(username, book_title, publisher_name, publication_date, is_favorite, is_wishlist, is_bucketlist)
JOIN book_tracker.users u ON u.username = v.username
JOIN book_tracker.books b ON b.book_title = v.book_title
JOIN book_tracker.publishers p ON p.publisher_name = v.publisher_name
JOIN book_tracker.book_publishers bp ON b.book_id = bp.book_id 
    AND p.publisher_id = bp.publisher_id 
    AND bp.publication_date = v.publication_date
ON CONFLICT (user_id, book_publisher_id) DO NOTHING;