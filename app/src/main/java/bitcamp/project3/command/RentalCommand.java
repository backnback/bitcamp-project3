package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;
import static bitcamp.project3.vo.Book.*;

import java.util.List;

public class RentalCommand extends AbstractCommand {

  private List<Book> bookList;
  private List<User> userList;


  private String[] menus = {"신규대출", "대출목록", "대출조회", "대출반납"};

  public RentalCommand(String menuTitle, List<Book> booklist, List<User> userlist) {
    super(menuTitle);
    this.bookList = booklist;
    this.userList = userlist;
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
        this.deleteRental();
        break;
    }
  }

  private void addRental() {
    String name = Prompt.input("회원 이름?");


    String title = Prompt.input("책이름?");
    for (Book book : bookList) {
      if (book.getName().contains(title)) {
        System.out.printf("%d.  %s\t\t%s\n", book.getNo(), book.getName(), currentStatus(book));
      }
    }
    int bookNo = Prompt.inputInt("대여할 도서 번호?");
    int index = bookList.indexOf(new Book(bookNo));
    if (index == -1) {
      System.out.println("없는 책입니다.");
      return;
    }
    Book book = bookList.get(index);
    if (book.getStatus() == Out) {
      System.out.println("이미 대여된 책입니다.");
    } else if (book.getStatus() == Reserved) {
      System.out.println("예약된 책입니다.");
    }

    book.setStatus(Out);
    System.out.printf("%s 책 대여가 완료되었습니다.\n", book.getName());
  }


  private void listRental() {
    System.out.println("번호\t\t책이름\t\t상태");
    for (Book book : bookList) {
      if (book.getStatus() == Out) {
        System.out.printf("%d\t\t%s\t\t%s\n", book.getNo(), book.getName(), currentStatus(book));
      }
    }
  }


  private void viewRental() {
    int userNo = Prompt.inputInt("회원번호?");
    int index = userList.indexOf(new User(userNo));
    if (index == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }

    User user = userList.get(index);

    System.out.printf("이름: %s\n", user.getName());
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

  private void deleteRental() {
    int userNo = Prompt.inputInt("회원번호?");
    int index = userList.indexOf(new User(userNo));
    if (index == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }

    User deletedUser = userList.remove(index);
    System.out.printf("'%s' 회원을 삭제 했습니다.\n", deletedUser.getName());
  }


  public String currentStatus(Book book) {
    String bookStatus = "";
    switch (book.getStatus()) {
      case Available -> bookStatus = "대출가능";
      case Out -> bookStatus = "대출중";
      case Reserved -> bookStatus = "예약중";
    }
    return bookStatus;
  }
}

