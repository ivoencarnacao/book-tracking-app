package dev.ivoencarnacao.service.entity;

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
@Table(name = "user_books", schema = "book_tracker", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "user_id", "book_publisher_id" }) })
public class UserBook {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_book_id")
  private Long userBookId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "book_publisher_id", nullable = false)
  private BookPublisher bookPublisher;

  @Column(name = "is_favorite", nullable = false)
  private boolean isFavorite;

  @Column(name = "is_wishlist", nullable = false)
  private boolean isWishlist;

  @Column(name = "is_bucketlist", nullable = false)
  private boolean isBucketlist;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private OffsetDateTime createdAt;

  protected UserBook() {
  }

  public UserBook(User user, BookPublisher bookPublisher) {
    this.user = user;
    this.bookPublisher = bookPublisher;

    this.isFavorite = false;
    this.isWishlist = false;
    this.isBucketlist = false;
  }

  public Long getUserBookId() {
    return userBookId;
  }

  public void setUserBookId(Long userBookId) {
    this.userBookId = userBookId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public BookPublisher getBookPublisher() {
    return bookPublisher;
  }

  public void setBookPublisher(BookPublisher bookPublisher) {
    this.bookPublisher = bookPublisher;
  }

  public boolean isFavorite() {
    return isFavorite;
  }

  public void setFavorite(boolean isFavorite) {
    this.isFavorite = isFavorite;
  }

  public boolean isWishlist() {
    return isWishlist;
  }

  public void setWishlist(boolean isWishlist) {
    this.isWishlist = isWishlist;
  }

  public boolean isBucketlist() {
    return isBucketlist;
  }

  public void setBucketlist(boolean isBucketlist) {
    this.isBucketlist = isBucketlist;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

}
