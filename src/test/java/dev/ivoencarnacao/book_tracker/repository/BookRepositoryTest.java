package dev.ivoencarnacao.book_tracker.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.ivoencarnacao.book_tracker.config.AbstractIntegrationTest;
import dev.ivoencarnacao.book_tracker.entity.Book;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Integration Tests for BookRepository")

public class BookRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private BookRepository bookRepository;

  @Test
  @DisplayName("Should return all books with all details (Authors and Publishers) fetched")
  void shouldReturnAllBooksWithDetails() {

    List<Book> allBooks = bookRepository.findAllWithDetails();

    assertThat(allBooks)
        .as("Query should find all books in the test databse")
        .isNotEmpty();

    assertThat(allBooks)
        .as("List should contain known books from seeds")
        .extracting(Book::getTitle)
        .contains("Spring Start Here", "Clean Architecture: A Craftsmans Guide to Software Structure and Design");

    assertThat(allBooks)
        .as("ALL returned Books should have their associations loaded")
        .allSatisfy(book -> {
          assertThat(book.getBookAuthors())
              .as("BookAuthors collection for '%s' should not be null", book.getTitle())
              .isNotNull();

          assertThat(book.getBookPublishers())
              .as("BookPublishers collection for '%s' should not be null", book.getTitle())
              .isNotNull();

          if (!book.getBookAuthors().isEmpty()) {
            assertThat(book.getBookAuthors().iterator().next().getAuthor())
                .as("Author for book '%s' should be loaded", book.getTitle())
                .isNotNull();

            assertThat(book.getBookAuthors().iterator().next().getAuthor().getName())
                .as("Author name for book '%s' should be loaded", book.getTitle())
                .isNotBlank();
          }

          if (!book.getBookPublishers().isEmpty()) {
            assertThat(book.getBookPublishers().iterator().next().getPublisher())
                .as("Publisher for book '%s' should be loaded", book.getTitle())
                .isNotNull();

            assertThat(book.getBookPublishers().iterator().next().getPublisher().getName())
                .as("Publisher name for book '%s' should be loaded", book.getTitle())
                .isNotBlank();
          }

        });

  }

  @Test
  @DisplayName("Should return a specific book by title with all details fetched")
  void shouldReturnBookByTitleWithDetails() {
    String titleToFind = "Spring Start Here";

    Optional<Book> optBook = bookRepository.findByTitleWithDetails(titleToFind);

    assertThat(optBook)
        .as("Book with title '%s' should be found", titleToFind)
        .isPresent();

    Book book = optBook.get();
    assertThat(book.getTitle())
        .as("Book title should be '%s'", titleToFind)
        .isEqualTo(titleToFind);

    assertThat(book.getBookAuthors())
        .as("BookAuthors should be fetched")
        .isNotEmpty();
    assertThat(book.getBookAuthors().iterator().next().getAuthor())
        .as("Nested author should be fetched")
        .isNotNull();

    assertThat(book.getBookAuthors().iterator().next().getAuthor().getName())
        .as("Nested author's name should be loaded")
        .isNotBlank();

    assertThat(book.getBookPublishers())
        .as("BookPublishers should be fetched")
        .isNotEmpty();
    assertThat(book.getBookPublishers().iterator().next().getPublisher())
        .as("Nested publisher should be fetched")
        .isNotNull();
    assertThat(book.getBookPublishers().iterator().next().getPublisher().getName())
        .as("Nested publisher's name should be loaded")
        .isNotBlank();

  }

  @Test
  @DisplayName("Should return an empty Optional when title does not exist")
  void shouldReturnEmptyOptionalWhenTitleDoesNotExist() {
    String titleToFind = "Non-Existing Book Title";

    Optional<Book> optBook = bookRepository.findByTitleWithDetails(titleToFind);

    assertThat(optBook)
        .as("Query should return an empty Optional for a non-existent title")
        .isEmpty();

  }
}
