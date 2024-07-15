package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Record;
import bitcamp.project3.vo.Rental;
import bitcamp.project3.vo.User;
import static bitcamp.project3.vo.Book.*;

import java.time.LocalDate;
import java.util.List;

public class RentalCommand extends AbstractCommand {

  private List<Book> bookList;
  private List<User> userList;
  private List<Record> recordList;
  private List<Rental> rentalList;


  private String[] menus = {"신규대출", "대출목록", "대출조회", "대출반납"};

  public RentalCommand(String menuTitle, List<Book> booklist, List<User> userlist,
      List<Record> recordList) {
    super(menuTitle);
    this.bookList = booklist;
    this.userList = userlist;
    this.recordList = recordList;

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
      case "대출반납":
        this.deleteUserInformation();
        break;
    }
  }


  private void addRental() {
    String name = Prompt.input("회원 이름?");
    for (User user : userList) {
      if (user.getName().contains(name)) {
        System.out.printf("%d.\t\t%s\n", user.getNo(), user.getName());
      }
    }
    int userNo = Prompt.inputInt("회원 번호 입력 :");
    int userIndex = userList.indexOf(new User(userNo));
    if (userIndex == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }
    User user = userList.get(userIndex);

    String title = Prompt.input("책이름?");
    for (Book book : bookList) {
      if (book.getName().contains(title)) {
        System.out.printf("%d.  %s\t\t%s\n", book.getNo(), book.getName(), currentStatus(book));
      }
    }
    int bookNo = Prompt.inputInt("대여할 도서 번호?");
    int bookIndex = bookList.indexOf(new Book(bookNo));
    if (bookIndex == -1) {
      System.out.println("없는 책입니다.");
      return;
    }
    Book book = bookList.get(bookIndex);
    if (book.getStatus() == Out) {
      System.out.println("이미 대여된 책입니다.");
      return;
    } else if (book.getStatus() == Reserved) {
      System.out.println("예약된 책입니다.");
      return;
    }

    LocalDate currentDate = LocalDate.now();
    int day = Prompt.inputInt("대여 몇 일?(3~7일)");
    LocalDate endDate = currentDate.plusDays(day);

    book.setStatus(Out);
    System.out.printf("%s 책 대여가 완료되었습니다.\n", book.getName());
    recordList.add(new Record(Record.getNextSeqNo(), currentDate, endDate, user, book, "대여중"));
  }


  private void listRental() {
    System.out.println("번호\t\t대여자\t\t대여책\t\t대여일");
    for (Record record : recordList) {
      User user = record.getUser();
      Book book = record.getBook();
      System.out.printf("%d\t\t%s\t\t%s\t\t%s - %s\t\t%s\n", record.getNo(), user.getName(),
          book.getName(), record.getStartDate(), record.getEndDate(), record.getComplete());
    }
    System.out.println("\n\n");

    while (true) {
      String command = Prompt.input("[1] 반납    [2] 기록 보기    [3] 기록삭제    [0] 이전");
      if (command.equals("1")) {
        completeRental();
        continue;
      } else if (command.equals("2")) {
        System.out.println("번호\t\t대여자\t\t대여책\t\t대여일");
        for (Record record : recordList) {
          User user = record.getUser();
          Book book = record.getBook();
          System.out.printf("%d\t\t%s\t\t%s\t\t%s - %s\t\t%s\n", record.getNo(), user.getName(),
              book.getName(), record.getStartDate(), record.getEndDate(), record.getComplete());
        }
        System.out.println("\n\n");
        continue;
      } else if (command.equals("0")) { // 이전 메뉴 선택
        return;
      }
      System.out.println("다시 입력하세요.");
    }
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
    if (completedRecord.getComplete().equals("반납 완료")) {
      System.out.printf("%d번 기록은 이미 반납 완료 처리되었습니다.\n", completedRecord.getNo());
      System.out.println("\n\n");
      return;
    }

    for (Book book : bookList) {
      if (book.getName().equals(completedRecord.getBook().getName())) {
        book.setStatus(Available);
      }
    }
    recordList.get(recordIndex).setComplete("반납 완료");
    System.out.printf("'%s'님의 [%s] 도서 반납을 완료했습니다.\n", completedRecord.getUser().getName(),
        completedRecord.getBook().getName());
    System.out.println("\n\n");
  }



    private void deleteUserInformation () {
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

      int bookNo = Prompt.inputInt("책 번호?");
      int index = bookList.indexOf(new Book(bookNo));
      if (index == -1) {
        System.out.println("없는 책입니다.");
        return;
      }

      Book book = bookList.get(index);
      Record record = recordList.get(index);

      record = recordList.remove(index);
      if (recordList == null) {
        System.out.println("다시 선택하세요.");
        return;
      } else {
        System.out.println("유저 정보를 삭제 했습니다.");
      }
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



