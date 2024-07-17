package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Record;
import bitcamp.project3.vo.Rental;
import bitcamp.project3.vo.User;
import static bitcamp.project3.vo.Book.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RentalCommand extends AbstractCommand {

  private static RentalCommand instance;

  private String redAnsi = "\033[31m";
  private String resetAnsi = "\033[0m";

  private List<Book> bookList;
  private List<User> userList;
  private List<Record> recordList;
  private List<Rental> rentalList;


  private String[] menus = {"신규대출", "대출목록", "대출조회"};

  public RentalCommand(String menuTitle, List<Book> booklist, List<User> userlist,
      List<Record> recordList) {
    super(menuTitle);
    this.bookList = booklist;
    this.userList = userlist;
    this.recordList = recordList;

    dummyData();
  }

  public static RentalCommand getInstance(String menuTitle, List<Book> booklist,
      List<User> userlist,
      List<Record> recordList) {
    if (instance == null) {
      instance = new RentalCommand(menuTitle, booklist, userlist, recordList);
    }
    return instance;
  }

  private void dummyData() {
    bookList.add(
        new Book(Book.getNextserialNo(), "자바란 무엇인가", "김민수", "비트캠프", LocalDate.now(), "교육"));
    bookList.add(
        new Book(Book.getNextserialNo(), "3일 동안 자바 부시기", "양지윤", "비트캠프", LocalDate.now(), "교육"));
    bookList.add(
        new Book(Book.getNextserialNo(), "너도 자바할 수 있어", "이선아", "비트캠프", LocalDate.now(), "교육"));
    bookList.add(
        new Book(Book.getNextserialNo(), "자바는 chat gpt", "김민수", "비트캠프", LocalDate.now(), "교육"));
    bookList.add(
        new Book(Book.getNextserialNo(), "고양이와 사는 삶", "장혜정", "비트캠프", LocalDate.now(), "수필"));
    bookList.add(
        new Book(Book.getNextserialNo(), "나의 인턴 회고록", "이선아", "비트캠프", LocalDate.now(), "수필"));
    bookList.add(
        new Book(Book.getNextserialNo(), "웃음 많은 아이", "장혜정", "부동산왕", LocalDate.now(), "소설"));

    userList.add(new User(User.getNextSeqNo(), "김민수", LocalDate.now()));
    userList.add(new User(User.getNextSeqNo(), "장혜정", LocalDate.now()));
    userList.add(new User(User.getNextSeqNo(), "양지윤", LocalDate.now()));
  }


  @Override
  protected String[] getMenus() {
    return menus;
  }

  @Override
  protected void processMenu(String menuName) {
    System.out.printf("[%s]\n", menuName);
    switch (menuName) {
      case "신규대출":
        this.addRental();
        break;
      case "대출조회":
        this.viewRental();
        break;
      case "대출목록":
        this.listRental();
        break;
    }
  }


  private void addRental() {
    String name = Prompt.input("회원 이름? (전체 : enter)");
    List<User> tempUserList = new ArrayList<>();
    System.out.println("┌─────────────────────┐");
    for (User user : userList) {
      if (user.getName().contains(name)) {
        System.out.printf("│%d.  %-14s│\n", user.getNo(), user.getName());
        tempUserList.add(user);
      }
    }

    if (tempUserList.isEmpty()) {
      System.out.println("│>>>>>>  Empty  <<<<<<│");
      System.out.println("└─────────────────────┘");
      return;
    }
    System.out.println("└─────────────────────┘");

    int userNo = Prompt.inputInt("회원 번호 입력 :");
    int userIndex = userList.indexOf(new User(userNo));
    if (userIndex == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }
    User user = userList.get(userIndex);

    String title = Prompt.input("책이름? (전체 : enter)");
    List<Book> tempBookList = new LinkedList<>();
    System.out.println("┌───────────────────────────────────────┐");
    for (Book book : bookList) {
      if (book.getName().contains(title)) {
        System.out.printf("  %d.  %s  :  %s%s%s\n", book.getNo(), book.getName(), redAnsi, currentStatus(book), resetAnsi);
        tempBookList.add(book);
      }
    }

    if (tempBookList.isEmpty()) {
      System.out.println("│>>>>>>>>>>>      Empty      <<<<<<<<<<<│");
      System.out.println("└───────────────────────────────────────┘");
      return;
    }

    Book book;
    while (true) {
      int bookNo = Prompt.inputInt("대여할 도서 번호?");
      int bookIndex = bookList.indexOf(new Book(bookNo));
      if (bookIndex == -1) {
        System.out.println("없는 책입니다.");
        continue;
      }
      book = bookList.get(bookIndex);
      if (book.getStatus() == Out) {
        System.out.println("이미 대여된 책입니다.");
        continue;
      } else if (book.getStatus() == Reserved) {
        System.out.println("예약된 책입니다.");
        continue;
      }
      break;
    }

    LocalDate currentDate = LocalDate.now();
    LocalDate endDate;
    int day = 0;
    while (true) {
      try {
        day = Prompt.inputInt("대여 몇 일?(1~7일)");
        if (day > 7) {
          day = 7;
          System.out.println("최대 7일 입니다.");
        } else if (day < 1) {
          day = 1;
          System.out.println("최소 1일 입니다.");
        }
        endDate = currentDate.plusDays(day);
        break;
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }

    book.setStatus(Out);
    System.out.printf("[%s] 책 대여가 완료되었습니다.\n", book.getName());
    recordList.add(new Record(Record.getNextSeqNo(), currentDate, endDate, user, book, "대여 중"));

  }


  private void listRental() {
    printList();

    while (true) {
      String command = Prompt.input("[1] 반납    [2] 기록 보기    [3] 기록삭제    [0] 이전");
      if (command.equals("1")) {
        completeRental();
        continue;
      } else if (command.equals("2")) {
        printList();
        continue;
      } else if (command.equals("3")) {
        deleteUserInformation();
        continue;
      } else if (command.equals("0")) { // 이전 메뉴 선택
        return;
      }
      System.out.println("다시 입력하세요.");
    }
  }

  private void printList() {
    String boldAnsi = "\033[1m";
    String blueAnsi = "\033[34m";
    String pinkAnsi = "\033[35m";

    String resetAnsi = "\033[0m";

    System.out.println(boldAnsi + pinkAnsi
        + "┌──────┬──────────┬─────────────────────────┬──────────────┬────────────────────────────┐"
        + resetAnsi);
    System.out.println(boldAnsi + pinkAnsi
        + "│ 번호 │  대여자  │   대출일자 ~ 반납기일   │   완료여부   │           대여책           │" + resetAnsi);
    System.out.println(boldAnsi + pinkAnsi
        + "└──────┴──────────┴─────────────────────────┴──────────────┴────────────────────────────┘"
        + resetAnsi);

    for (Record record : recordList) {
      User user = record.getUser();
      Book book = record.getBook();
      System.out.printf("    %-5d %-6s %-10s - %-14s %-8s %-22s \n", record.getNo(), user.getName(),
          record.getStartDate(), record.getEndDate(), record.getComplete(), book.getName());
    }
    System.out.println("\n\n");
  }


  private void viewRental() {
    String userName = Prompt.input("회원 이름?");
    User selectedUser = null;

    for (User user : userList) {
      if (user.getName().contains(userName)) {
        System.out.printf("%d.\t%s\n", user.getNo(), user.getName());
      }
    }

    int userNo = Prompt.inputInt("회원 번호 입력:");
    for (User user : userList) {
      if (user.getNo() == userNo) {
        selectedUser = user;
        break;
      }
    }

    if (selectedUser == null) {
      System.out.println("없는 회원입니다.");
      return;
    }

    System.out.println("번호\t책 이름\t대출 날짜\t\t\t반납 날짜\t\t대출 여부");
    for (Record record : recordList) {
      if (record.getUser().equals(selectedUser)) {
        Book book = record.getBook();
        System.out.printf("%d\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\n", book.getNo(), book.getName(),
            record.getStartDate(), record.getEndDate(), currentStatus(book));
      }
    }
  }

  private void updateRental() {
    int userNo = Prompt.inputInt("회원번호?");
    int index = userList.indexOf(new User(userNo));
    if (index == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }

    User user = userList.get(index);

    user.setName(Prompt.input("이름(%s)?", user.getName()));
    System.out.println("변경 했습니다.");
  }


  private void completeRental() {
    int recordNo = Prompt.inputInt("기록 번호?");
    int recordIndex = recordList.indexOf(new Record(recordNo));
    if (recordIndex == -1) {
      System.out.println("없는 기록입니다.");
      return;
    }

    Record completedRecord = recordList.get(recordIndex);
    if (completedRecord.getComplete().equals("반납완료")) {
      System.out.printf("%d번 기록은 이미 반납 완료 처리되었습니다.\n", completedRecord.getNo());
      System.out.println("\n\n");
      return;
    }

    for (Book book : bookList) {
      if (book.getName().equals(completedRecord.getBook().getName())) {
        book.setStatus(Available);
      }
    }
    recordList.get(recordIndex).setComplete("반납완료");
    System.out.printf("'%s'님의 [%s] 도서 반납을 완료했습니다.\n", completedRecord.getUser().getName(),
        completedRecord.getBook().getName());
    System.out.println("\n\n");
  }


  private void deleteUserInformation() {
    String boldAnsi = "\033[1m";
    String blueAnsi = "\033[34m";
    String pinkAnsi = "\033[35m";

    String resetAnsi = "\033[0m";

    System.out.println(boldAnsi + pinkAnsi
        + "┌──────┬──────────┬─────────────────────────┬──────────────┬────────────────────────────┐"
        + resetAnsi);
    System.out.println(boldAnsi + pinkAnsi
        + "│ 번호 │  대여자  │   대출일자 ~ 반납기일   │   완료여부   │           대여책           │" + resetAnsi);
    System.out.println(boldAnsi + pinkAnsi
        + "└──────┴──────────┴─────────────────────────┴──────────────┴────────────────────────────┘"
        + resetAnsi);

    for (Record record : recordList) {
      User user = record.getUser();
      Book book = record.getBook();
      System.out.printf("    %-5d %-6s %-10s - %-14s %-8s %-22s \n", record.getNo(), user.getName(),
          record.getStartDate(), record.getEndDate(), record.getComplete(), book.getName());
    }
    System.out.println("\n\n");

    int userDataNo = Prompt.inputInt("사용자 번호 입력:");
    int index = recordList.indexOf(new Record(userDataNo));
    if (index == -1) {
      System.out.println("사용자 번호가 없습니다.");
      return;
    }
    Record deleteRecord = recordList.remove(index);
    System.out.printf("'%s' 사용자 정보를 삭제 했습니다.\n", deleteRecord.getNo());
  }

    public String currentStatus (Book book){
      String bookStatus = "";
      switch (book.getStatus()) {
        case Available -> bookStatus = "대출가능";
        case Out -> bookStatus = "대출중";
        case Reserved -> bookStatus = "예약중";
      }
      return bookStatus;
    }
  }



