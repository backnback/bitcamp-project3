package bitcamp.project3.vo;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Book {

  public static final int Available = 0;
  public static final int Out = 1;
  public static final int Reserved = 2;

  private static int serialNo;

  private int no;
  private String name;
  private Date bookRegistrationDate;
  private String author;
  private String publisher;
  private String genre;
  private int rentalPrice;
  private int status;
  private String publicationYear;
  private int borrowDays;
  private LocalDate registrationDate;
  private boolean isBorrowed;

  public Book() {}

  public Book(int no) {
    this.no = no;
  }

  public Book(String name) {
    this.name = name;
  }

  public Book(int no, String name, String author, String publisher, LocalDate registrationDate, String genre) {
    this.no = no;
    this.name = name;
    this.author = author;
    this.publisher = publisher;
    this.registrationDate = registrationDate;
    this.genre = genre;
  }

  public Book(String name, int borrowDays) {
    this.name = name;
    this.borrowDays = borrowDays;
  }

  public boolean isBorrowed() {
    return isBorrowed;
  }

  public void setBorrowed(boolean borrowed) {
    isBorrowed = borrowed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Book book = (Book) o;
    return no == book.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
  }

  public static int getNextserialNo() {
    return ++serialNo;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Date getBookRegistrationDate() {
    return bookRegistrationDate;
  }

  public void setRegistrationDate(Date bookCreatedDate) {
    this.bookRegistrationDate = bookCreatedDate;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public int getRentalPrice() {
    return rentalPrice;
  }

  public void setRentalPrice(int rentalPrice) {
    this.rentalPrice = rentalPrice;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(String publicationYear) {
    this.publicationYear = publicationYear;
  }

  public int getBorrowDays() {
    return borrowDays;
  }

  public void setBorrowDays(int borrowDays) {
    this.borrowDays = borrowDays;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }
}
