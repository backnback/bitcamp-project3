package bitcamp.project3.vo;

import java.util.Date;
import java.util.Objects;

public class Book {

  public static final int Available = 0;
  public static final int Out = 1;
  public static final int Reserved = 2;

  private static int serialNo;


  private int no;
  private String title;
  private Date bookRegistrationDate;
  private String author;
  private String publisher;
  private String genre;
  private int rentalPrice;
  private int status;
  private String publicationYear;


  public Book() {}

  public Book(int no) {
    this.no = no;
  }

  public Book(int no, String title, Date bookRegistrationDate, String author, String publisher,
      String genre, int rentalPrice, int status, String publicationYear) {
    this.no = no;
    this.title = title;
    this.bookRegistrationDate = bookRegistrationDate;
    this.author = author;
    this.publisher = publisher;
    this.genre = genre;
    this.rentalPrice = rentalPrice;
    this.status = status;
    this.publicationYear = publicationYear;
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

  public static int getSerialNo() {
    return ++serialNo;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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
}
