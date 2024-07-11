package bitcamp.project3.vo;

public class Book {

  public static final int Available = 0;
  public static final int Out = 1;
  public static final int Reserved = 2;

  private static int serialNo;


  private int no;
  private String title;
  private String bookCreatedDate;
  private String author;
  private String publisher;
  private String genre;
  private int rentalPrice;
  private int status;
  private String publicationYear;


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

  public String getBookCreatedDate() {
    return bookCreatedDate;
  }

  public void setBookCreatedDate(String bookCreatedDate) {
    this.bookCreatedDate = bookCreatedDate;
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
