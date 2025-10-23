package dev.ivoencarnacao.book_tracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.ivoencarnacao.book_tracker.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

        @Query("SELECT DISTINCT b FROM Book b " +
                        "LEFT JOIN FETCH b.bookAuthors ba " +
                        "LEFT JOIN FETCH ba.author a " +
                        "LEFT JOIN FETCH b.bookPublishers bp " +
                        "LEFT JOIN FETCH bp.publisher p")
        List<Book> findAllWithDetails();

        @Query("SELECT b FROM Book b " +
                        "LEFT JOIN FETCH b.bookAuthors ba " +
                        "LEFT JOIN FETCH b.bookPublishers bp " +
                        "WHERE b.title = :title")
        Optional<Book> findByTitleWithDetails(String title);

}
