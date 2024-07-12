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

  public Record(){
  }

  public Record(int no) {
    this.no = no;
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
}
