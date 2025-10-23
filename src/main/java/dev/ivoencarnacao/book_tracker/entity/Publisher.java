package dev.ivoencarnacao.book_tracker.entity;

import java.time.OffsetDateTime;

import java.util.HashSet;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers", schema = "book_tracker")
public class Publisher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "publisher_id")
  private Long id;

  @Column(name = "publisher_name", nullable = false, length = 255, unique = true)
  private String name;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
  private OffsetDateTime createdAt;

  @OneToMany(mappedBy = "publisher", orphanRemoval = true)
  private Set<BookPublisher> bookPublishers = new HashSet<>();

  protected Publisher() {
  }

  public Publisher(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Set<BookPublisher> getBookPublishers() {
    return bookPublishers;
  }

  public void setBookPublishers(Set<BookPublisher> bookPublishers) {
    this.bookPublishers = bookPublishers;
  }

}
