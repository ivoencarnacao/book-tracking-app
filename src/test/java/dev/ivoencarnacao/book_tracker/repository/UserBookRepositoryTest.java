package dev.ivoencarnacao.book_tracker.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.ivoencarnacao.book_tracker.config.AbstractIntegrationTest;
import dev.ivoencarnacao.book_tracker.entity.UserBook;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserBookRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private UserBookRepository userBookRepository;

  @Test
  void testFindByUsernameWithDetails_ReturnsFetchedData() {

    String username = "user1";

    List<UserBook> userBooks = userBookRepository.findByUsernameWithDetails(username);

    assertThat(userBooks).isNotEmpty();

    UserBook firstUserBook = userBooks.get(0);

    assertThat(firstUserBook.getUser().getUsername()).isEqualTo(username);

    assertThat(firstUserBook.getBookPublisher()).isNotNull();

    assertThat(firstUserBook.getBookPublisher().getBook().getTitle()).isNotNull();

    assertThat(firstUserBook.getBookPublisher().getBook().getBookAuthors()).isNotEmpty();

    assertThat(firstUserBook.getBookPublisher().getBook().getBookAuthors()
        .iterator().next().getAuthor().getName()).isNotNull();

    boolean hasSpringStartHereBook = userBooks.stream()
        .anyMatch(ub -> ub.getBookPublisher().getBook().getTitle().equals("Spring Start Here"));
    assertThat(hasSpringStartHereBook).isTrue();

  }

}
