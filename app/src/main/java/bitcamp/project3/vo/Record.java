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

}
