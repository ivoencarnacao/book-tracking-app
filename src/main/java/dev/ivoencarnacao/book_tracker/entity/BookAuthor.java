package dev.ivoencarnacao.book_tracker.entity;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "book_authors", schema = "book_tracker", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "book_id", "author_id" })
})
public class BookAuthor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_author_id")
  private Long bookAuthorId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "author_id", nullable = false)
  private Author author;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private OffsetDateTime createdAt;

  protected BookAuthor() {
  }

  public BookAuthor(Book book, Author author) {
    this.book = book;
    this.author = author;
  }

  public Long getBookAuthorId() {
    return bookAuthorId;
  }

  public void setBookAuthorId(Long bookAuthorId) {
    this.bookAuthorId = bookAuthorId;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

}
