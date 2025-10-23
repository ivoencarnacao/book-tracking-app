package dev.ivoencarnacao.book_tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ivoencarnacao.book_tracker.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  Optional<Author> findByName(String name);

}
