package dev.ivoencarnacao.service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ivoencarnacao.service.dto.BookFormDto;
import dev.ivoencarnacao.service.dto.UserBookDetailDto;
import dev.ivoencarnacao.service.entity.BookPublisher;
import dev.ivoencarnacao.service.entity.User;
import dev.ivoencarnacao.service.entity.UserBook;
import dev.ivoencarnacao.service.mapper.UserBookMapper;
import dev.ivoencarnacao.service.repository.UserBookRepository;
import dev.ivoencarnacao.service.repository.UserRepository;

@Service
public class UserBookService {

  private static final Logger logger = LoggerFactory.getLogger(UserBookService.class);

  private final UserBookRepository userBookRepository;
  private final UserBookMapper userBookMapper;
  private final UserRepository userRepository;
  private final BookService bookService;

  public UserBookService(UserBookRepository userBookRepository, UserBookMapper userBookMapper,
      UserRepository userRepository, BookService bookService) {
    this.userBookRepository = userBookRepository;
    this.userBookMapper = userBookMapper;
    this.userRepository = userRepository;
    this.bookService = bookService;
  }

  @Transactional(readOnly = true)
  public List<UserBookDetailDto> getUserBooks(String username) {

    List<UserBook> userBooks = userBookRepository.findByUsernameWithDetails(username);

    return userBooks.stream()
        .map(userBookMapper::toDto)
        .collect(Collectors.toList());

  }

  @Transactional
  public void addUserBook(BookFormDto bookFormDto, String username) {
    BookPublisher bookPublisher = bookService.createOrFindBookPublisher(bookFormDto);

    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalStateException("O utilizador autenticado não foi encontrado."));

    boolean alreadyExists = user.getUserBooks().stream()
        .anyMatch(ub -> ub.getBookPublisher().equals(bookPublisher));

    if (!alreadyExists) {
      UserBook userBook = new UserBook(user, bookPublisher);
      userBookRepository.save(userBook);
      logger.info("A edição do livro '{}' foi adicionada à lista do utilizador '{}'.",
          bookPublisher.getBook().getTitle(), username);
    } else {
      logger.info("O utilizador '{}' já tem esta edição do livro na sua lista.", username);
    }

  }

  @Transactional
  public void updateUserBookStatus(Long userBookId, boolean isFavorite, boolean isWishlist, boolean isBucketlist,
      String username) {

    logger.info("A atualizar o estado do livro do utilizador com ID: {} para o utilizador '{}'", userBookId, username);

    UserBook userBook = userBookRepository.findById(userBookId)
        .orElseThrow(() -> new IllegalStateException(
            "Registo do livro do utilizador com ID " + userBookId + " não encontrado."));

    if (!userBook.getUser().getUsername().equals(username)) {
      throw new AccessDeniedException("Não tem permissão para alterar este livro.");
    }

    userBook.setFavorite(isFavorite);
    userBook.setWishlist(isWishlist);
    userBook.setBucketlist(isBucketlist);

    userBookRepository.save(userBook);
    logger.info("Estado do livro do utilizador com ID {} atualizado com sucesso.", userBookId);
  }

  public void removeUserBook(Long userBookId, String username) {

    logger.info("A remover o livro com o ID: {} do utilizador '{}'", userBookId, username);

    UserBook userBook = userBookRepository.findById(userBookId)
        .orElseThrow(() -> new IllegalStateException(
            "O registo do livro do utilizador com ID: " + userBookId + " não foi encontrado."));

    if (!userBook.getUser().getUsername().equals(username)) {
      logger.warn("Tentativa falhada: O utilizador '{}' tentou remover um livro do utilizador '{}'.",
          username, userBook.getUser().getUsername());
      throw new AccessDeniedException("Não tem permissão para remover este livro.");
    }

    userBookRepository.delete(userBook);
    logger.info("O livro '{}' foi removido com sucesso do utilizador '{}'.",
        userBook.getBookPublisher().getBook().getTitle(), username);
  }

}
