package bitcamp.project3.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

// 메모리 설계도
public class User {

  private static int seqNo;

  private int no;
  private String name;
  private LocalDate registrationDate;

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }


  public User() {
  }

  public User(int no) {
    this.no = no;
  }

  public User(int no, String name, LocalDate registrationDate) {
    this.no = no;
    this.name = name;
    this.registrationDate = registrationDate;
  }

  public User(String name) {
    this.name = name;
  }

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return no == user.no;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
