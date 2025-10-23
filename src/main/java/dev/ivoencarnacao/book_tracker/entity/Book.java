package dev.ivoencarnacao.book_tracker.entity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books", schema = "book_tracker")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  private Long id;

  @Column(name = "book_title", nullable = false, length = 255, unique = true)
  private String title;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
  private OffsetDateTime createdAt;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<BookAuthor> bookAuthors = new HashSet<>();

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<BookPublisher> bookPublishers = new HashSet<>();

  protected Book() {
  }

  public Book(String title) {
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Set<BookAuthor> getBookAuthors() {
    return bookAuthors;
  }

  public void setBookAuthors(Set<BookAuthor> bookAuthors) {
    this.bookAuthors = bookAuthors;
  }

  public Set<BookPublisher> getBookPublishers() {
    return bookPublishers;
  }

  public void setBookPublishers(Set<BookPublisher> bookPublishers) {
    this.bookPublishers = bookPublishers;
  }

  public void addAuthor(Author author) {

    boolean alreadyExists = this.bookAuthors.stream()
        .anyMatch(ba -> ba.getBook().equals(this) && ba.getAuthor().equals(author));

    if (!alreadyExists) {
      BookAuthor bookAuthor = new BookAuthor(this, author);
      this.bookAuthors.add(bookAuthor);
      author.getBookAuthors().add(bookAuthor);
    }

  }

  public void removeAuthor(Author author) {
    for (var iterator = this.bookAuthors.iterator(); iterator.hasNext();) {
      BookAuthor bookAuthor = iterator.next();
      if (bookAuthor.getBook().equals(this) && bookAuthor.getAuthor().equals(author)) {
        iterator.remove();
        bookAuthor.getAuthor().getBookAuthors().remove(bookAuthor);
        bookAuthor.setBook(null);
        bookAuthor.setAuthor(null);
      }
    }
  }

  public void addPublisher(Publisher publisher, LocalDate publicationDate) {

    boolean alreadyExists = this.bookPublishers.stream()
        .anyMatch(bp -> bp.getBook().equals(this) &&
            bp.getPublisher().equals(publisher) &&
            Objects.equals(bp.getPublicationDate(), publicationDate));

    if (!alreadyExists) {
      BookPublisher bookPublisher = new BookPublisher(this, publisher, publicationDate);
      this.bookPublishers.add(bookPublisher);
      publisher.getBookPublishers().add(bookPublisher);
    }

  }

  public void removePublisher(Publisher publisher, LocalDate publicationDate) {
    for (var iterator = this.bookPublishers.iterator(); iterator.hasNext();) {
      BookPublisher bookPublisher = iterator.next();
      if (bookPublisher.getBook().equals(this) && bookPublisher.getPublisher().equals(publisher)
          && Objects.equals(bookPublisher.getPublicationDate(), publicationDate)) {
        iterator.remove();
        bookPublisher.getPublisher().getBookPublishers().remove(bookPublisher);
        bookPublisher.setBook(null);
        bookPublisher.setPublisher(null);
        break;
      }
    }
  }

}
