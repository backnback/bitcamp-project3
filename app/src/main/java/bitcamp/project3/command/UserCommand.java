package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UserCommand extends AbstractCommand {

  private ArrayList<User> userList = new ArrayList<>();
  private ArrayList<Book> bookList = new ArrayList<>();

  public UserCommand(String menuTitle, List<User> userList) {
    super(menuTitle);
  }

  @Override
  public void execute(Stack<String> menuPath) {
    System.out.println("<대출 메뉴>");
    this.borrowBook();
  }

  @Override
  protected String[] getMenus() {
    return new String[] { "대출", "반납", "조회" };
  }

  @Override
  protected void processMenu(String menuName) {
    // 필요하지 않음
  }

  private void borrowBook() {
    String userName = Prompt.input("이름을 입력하세요: ");
    String bookTitle = Prompt.input("책 이름을 입력하세요: ");
    int borrowDays = Prompt.inputInt("기간을 입력하세요: ");

    User user = new User(userName);
    Book book = new Book(bookTitle, borrowDays);

    userList.add(user);
    bookList.add(book);

  }
}
