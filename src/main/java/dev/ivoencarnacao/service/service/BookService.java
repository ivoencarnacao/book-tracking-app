package dev.ivoencarnacao.service.service;

import dev.ivoencarnacao.service.entity.Publisher;
import dev.ivoencarnacao.service.mapper.BookMapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ivoencarnacao.service.dto.BookDetailDto;
import dev.ivoencarnacao.service.dto.BookFormDto;
import dev.ivoencarnacao.service.entity.Author;
import dev.ivoencarnacao.service.entity.Book;
import dev.ivoencarnacao.service.entity.BookPublisher;
import dev.ivoencarnacao.service.repository.AuthorRepository;
import dev.ivoencarnacao.service.repository.BookRepository;
import dev.ivoencarnacao.service.repository.PublisherRepository;

@Service
public class BookService {

  private static final Logger logger = LoggerFactory.getLogger(BookService.class);

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final PublisherRepository publisherRepository;
  private final BookMapper bookMapper;

  public BookService(BookRepository bookRepository, AuthorRepository authorRepository,
      PublisherRepository publisherRepository, BookMapper bookMapper) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.publisherRepository = publisherRepository;
    this.bookMapper = bookMapper;
  }

  @Transactional(readOnly = true)
  public List<BookDetailDto> getAllBooks() {
    List<Book> books = bookRepository.findAllWithDetails();

    List<BookDetailDto> bookDetails = new ArrayList<>();
    for (Book book : books) {
      if (book.getBookPublishers().isEmpty()) {
      } else {
        book.getBookPublishers().forEach(bookPublisher -> {
          bookDetails.add(bookMapper.toDto(bookPublisher));
        });
      }
    }
    return bookDetails;
  }

  @Transactional
  public BookPublisher createOrFindBookPublisher(BookFormDto bookFormDto) {

    logger.info("A garantir a existência da edição {}", bookFormDto);

    Author author = findAuthor(bookFormDto.authorName())
        .orElseGet(() -> createAuthor(bookFormDto.authorName()));

    Publisher publisher = findPublisher(bookFormDto.publisherName())
        .orElseGet(() -> createPublisher(bookFormDto.publisherName()));

    Book book = findBook(bookFormDto.title())
        .orElseGet(() -> createBook(bookFormDto.title()));

    LocalDate publicationDate = parsePublicationDate(bookFormDto.publicationDate());

    book.addAuthor(author);
    book.addPublisher(publisher, publicationDate);
    bookRepository.save(book);

    logger.debug("A procurar a edição...");
    return findBookPublisher(book, publisher, publicationDate);
  }

  private Optional<Author> findAuthor(String name) {
    logger.debug("A procurar o autor: '{}'", name);
    return authorRepository.findByName(name);
  }

  private Optional<Publisher> findPublisher(String name) {
    logger.debug("A procurar a editora: '{}'", name);
    return publisherRepository.findByName(name);
  }

  private Optional<Book> findBook(String title) {
    logger.debug("A procurar o livro: '{}'", title);
    return bookRepository.findByTitleWithDetails(title);
  }

  private Author createAuthor(String name) {
    logger.info("Autor '{}' não encontrado. A criar novo.", name);
    return authorRepository.save(new Author(name));
  }

  private Publisher createPublisher(String name) {
    logger.info("Editora '{}' não encontrada. A criar nova.", name);
    return publisherRepository.save(new Publisher(name));
  }

  private Book createBook(String title) {
    logger.info("Livro '{}' não encontrado. A criar novo.", title);
    return bookRepository.save(new Book(title));
  }

  private LocalDate parsePublicationDate(String dateString) {
    if (dateString == null || dateString.isBlank()) {
      return null;
    }
    try {
      return LocalDate.parse(dateString);
    } catch (DateTimeParseException e) {
      logger.warn("Formato de data inválido recebido: '{}'. A data de publicação será nula.", dateString, e);
      return null;

    }
  }

  private BookPublisher findBookPublisher(Book book, Publisher publisher, LocalDate publicationDate) {
    return book.getBookPublishers().stream()
        .filter(bookPublisher -> bookPublisher.getPublisher().equals(publisher)
            && (publicationDate == null
                ? bookPublisher.getPublicationDate() == null
                : publicationDate.equals(bookPublisher.getPublicationDate())))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException(
            "A edição do livro não foi encontrada após a sua criação."));

  }

}
