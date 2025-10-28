package dev.ivoencarnacao.book_tracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.ivoencarnacao.book_tracker.dto.BookFormDto;
import dev.ivoencarnacao.book_tracker.entity.Author;
import dev.ivoencarnacao.book_tracker.entity.Book;
import dev.ivoencarnacao.book_tracker.entity.BookPublisher;
import dev.ivoencarnacao.book_tracker.entity.Publisher;
import dev.ivoencarnacao.book_tracker.mapper.BookMapper;
import dev.ivoencarnacao.book_tracker.repository.AuthorRepository;
import dev.ivoencarnacao.book_tracker.repository.BookRepository;
import dev.ivoencarnacao.book_tracker.repository.PublisherRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Tests for BookService")
class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @Mock
  private AuthorRepository authorRepository;

  @Mock
  private PublisherRepository publisherRepository;

  @Mock
  private BookMapper bookMapper;

  @InjectMocks
  private BookService bookService;

  private BookFormDto bookFormDto;
  private Author author;
  private Publisher publisher;
  private Book book;
  private LocalDate publicationDate;

  @BeforeEach
  void setUp() {

    publicationDate = LocalDate.parse("2025-01-01");
    bookFormDto = new BookFormDto(
        "New Title",
        "New Author",
        "New Publisher",
        "2025-01-01");

    author = new Author("New Author");
    publisher = new Publisher("New Publisher");

    book = new Book("New Title");
  }

  @Test
  @DisplayName("Should create new Author, Publisher, and Book when they do not exist")
  void shouldCreateNewEntitiesWhenTheyDoNotExist() {

    when(authorRepository.findByName("New Author")).thenReturn(Optional.empty());
    when(publisherRepository.findByName("New Publisher")).thenReturn(Optional.empty());
    when(bookRepository.findByTitleWithDetails("New Title")).thenReturn(Optional.empty());

    when(authorRepository.save(any(Author.class))).thenReturn(author);
    when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher);
    when(bookRepository.save(any(Book.class))).thenReturn(book);

    BookPublisher result = bookService.createOrFindBookPublisher(bookFormDto);

    verify(authorRepository).save(any(Author.class));
    verify(publisherRepository).save(any(Publisher.class));

    verify(bookRepository, times(2)).save(any(Book.class));

    assertThat(result)
        .as("The returned BookPublisher should not be null")
        .isNotNull();

    assertThat(result.getBook())
        .as("The book in the returned BookPublisher should be the one that was created")
        .isEqualTo(book);
    assertThat(result.getPublisher())
        .as("The publisher in the returned BookPublisher should be the one that was created")
        .isEqualTo(publisher);
    assertThat(result.getPublicationDate())
        .as("The publication date should match the one from the DTO")
        .isEqualTo(publicationDate);

  }

}
