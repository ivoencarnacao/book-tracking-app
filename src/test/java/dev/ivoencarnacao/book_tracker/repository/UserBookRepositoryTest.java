package dev.ivoencarnacao.book_tracker.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.ivoencarnacao.book_tracker.config.AbstractIntegrationTest;
import dev.ivoencarnacao.book_tracker.entity.UserBook;

@DataJpaTest
@DisplayName("Integration Tests for UserBookRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserBookRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private UserBookRepository userBookRepository;

  @Test
  @DisplayName("Should return user books with all details (JOIN FETCH) when username exists")
  void shouldReturnUserBooksWithDetailsWhenUsernameExists() {

    String usernameToFind = "user1";

    List<UserBook> userBooks = userBookRepository.findByUsernameWithDetails(usernameToFind);

    assertThat(userBooks)
        .as("Query should find books for 'user1' (based on seeds)")
        .isNotEmpty();

    assertThat(userBooks)
        .as("Book list should contain 'Spring Start Here' (based on seeds)")
        .extracting(ub -> ub.getBookPublisher().getBook().getTitle())
        .contains("Spring Start Here");

    assertThat(userBooks)
        .as("ALL returned UserBooks should have their associations (Author, Book, Publisher) loaded")
        .allSatisfy(ub -> {
          assertThat(ub.getUser())
              .as("User should not be null")
              .isNotNull();
          assertThat(ub.getUser().getUsername())
              .as("Username should match the requested one")
              .isEqualTo(usernameToFind);

          assertThat(ub.getBookPublisher())
              .as("BookPublisher should not be null")
              .isNotNull();
          assertThat(ub.getBookPublisher().getPublisher())
              .as("Publisher should not be null")
              .isNotNull();
          assertThat(ub.getBookPublisher().getPublisher().getName())
              .as("Publisher name should not be null or blank")
              .isNotBlank();

          assertThat(ub.getBookPublisher().getBook())
              .as("Book should not be null")
              .isNotNull();
          assertThat(ub.getBookPublisher().getBook().getTitle())
              .as("Book title should not be null or blank")
              .isNotBlank();

          assertThat(ub.getBookPublisher().getBook().getBookAuthors())
              .as("BookAuthors collection (LEFT JOIN) should not be null, even if empty")
              .isNotNull();

          if (!ub.getBookPublisher().getBook().getBookAuthors().isEmpty()) {
            assertThat(ub.getBookPublisher().getBook().getBookAuthors().iterator().next().getAuthor())
                .as("Nested author should be fetched")
                .isNotNull();
            assertThat(ub.getBookPublisher().getBook().getBookAuthors().iterator().next().getAuthor().getName())
                .as("Nested author's name should be loaded")
                .isNotBlank();

          }
        });

  }

  @Test
  @DisplayName("Should return ALL UserBooks from ALL users with all details (JOIN FETCH)")
  void shouldReturnAllUserBooksWithDetails() {

    List<UserBook> allUserBooks = userBookRepository.findAllWithDetails();

    assertThat(allUserBooks)
        .as("Query should find ALL UserBooks in the test database")
        .isNotEmpty();

    assertThat(allUserBooks)
        .as("List should contain books from both 'user1' and 'user2' (based on seeds)")
        .extracting(ub -> ub.getUser().getUsername())
        .contains("user1", "user2");

    assertThat(allUserBooks)
        .as("ALL returned UserBooks should have their associations (Author, Book, Publisher) loaded")
        .allSatisfy(ub -> {
          assertThat(ub.getUser())
              .as("User should not be null")
              .isNotNull();
          assertThat(ub.getBookPublisher())
              .as("BookPublisher should not be null")
              .isNotNull();
          assertThat(ub.getBookPublisher().getPublisher())
              .as("Publisher should not be null")
              .isNotNull();
          assertThat(ub.getBookPublisher().getBook())
              .as("Book should not be null")
              .isNotNull();
          assertThat(ub.getBookPublisher().getBook().getBookAuthors())
              .as("BookAuthors collection (LEFT JOIN) should not be null, even if empty")
              .isNotNull();

          if (!ub.getBookPublisher().getBook().getBookAuthors().isEmpty()) {
            assertThat(ub.getBookPublisher().getBook().getBookAuthors().iterator().next().getAuthor())
                .as("Nested author should be fetched")
                .isNotNull();
            assertThat(ub.getBookPublisher().getBook().getBookAuthors().iterator().next().getAuthor().getName())
                .as("Nested author's name should be loaded")
                .isNotBlank();

          }
        });

  }

  @Test
  @DisplayName("Should return an empty list when username does not exist")
  void shouldReturnEmptyListWhenUsernameDoesNotExist() {

    String usernameToFind = "Non-Existing User";

    List<UserBook> userBooks = userBookRepository.findByUsernameWithDetails(usernameToFind);

    assertThat(userBooks)
        .as("Query should return an empty list for a non-existent user")
        .isEmpty();
  }

}