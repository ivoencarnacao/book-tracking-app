package dev.ivoencarnacao.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.ivoencarnacao.service.entity.UserBook;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {

  @Query("SELECT ub FROM UserBook ub " +
      "JOIN FETCH ub.user u " +
      "JOIN FETCH ub.bookPublisher bp " +
      "JOIN FETCH bp.book b " +
      "JOIN FETCH bp.publisher p " +
      "LEFT JOIN FETCH b.bookAuthors ba " +
      "LEFT JOIN FETCH ba.author a " +
      "WHERE u.username = :username")
  List<UserBook> findByUsernameWithDetails(String username);

  @Query("SELECT ub FROM UserBook ub " +
      "JOIN FETCH ub.user u " +
      "JOIN FETCH ub.bookPublisher bp " +
      "JOIN FETCH bp.book b " +
      "JOIN FETCH bp.publisher p " +
      "LEFT JOIN FETCH b.bookAuthors ba " +
      "LEFT JOIN FETCH ba.author a")
  List<UserBook> findAllWithDetails();

}
