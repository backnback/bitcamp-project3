package bitcamp.project3.vo;

import java.time.LocalDate;
import java.util.Objects;

public class Record {

  private static int seqNo;

  private int no;
  private LocalDate startDate;
  private LocalDate endDate;

  private User user;
  private Book book;


  public static int getNextSeqNo() {
    return ++seqNo;
  }

  public Record() {

  }

  public Record(int no) {
    this.no = no;
  }


  public Record(int no, LocalDate startDate, LocalDate endDate, User user, Book book) {
    this.no = no;
    this.startDate = startDate;
    this.user = user;
    this.book = book;
    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Record record = (Record) o;
    return no == record.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
  }


  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
