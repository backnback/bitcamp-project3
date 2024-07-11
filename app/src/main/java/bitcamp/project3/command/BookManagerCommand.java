package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.List;

public class BookManagerCommand extends AbstractCommand {

  private List<Book> bookList;
  private String[] menus = {"등록", "목록", "조회", "변경", "삭제"};

  public BookManagerCommand(String menuTitle, List<Book> list) {
    super(menuTitle);
    this.bookList = list;
  }

  @Override
  protected String[] getMenus() {
    return menus;
  }

  @Override
  protected void processMenu(String menuName) {
    System.out.printf("[%s]\n", menuName);
    switch (menuName) {
      case "등록":
        this.addBook();
        break;
      case "조회":
        this.viewBook();
        break;
      case "목록":
        this.listBook();
        break;
      case "변경":
        this.updateBook();
        break;
      case "삭제":
        this.deleteBook();
        break;
    }
  }

  public void addBook() {
    Book book = new Book();
    book.setTitle(Prompt.input("책이름?"));
    book.setNo(User.getNextSeqNo());
    bookList.add(book);
  }

  private void listBook() {
    System.out.println("번호 책이름");
    for (Book book : bookList) {
      System.out.printf("%d %s\n", book.getNo(), book.getTitle());
    }
  }

  private void viewBook() {
    int bookNo = Prompt.inputInt("책 번호?");
    int index = bookList.indexOf(new Book(bookNo));
    if (index == -1) {
      System.out.println("없는 책입니다.");
      return;
    }

    Book book = bookList.get(index);

    System.out.printf("책 이름: %s\n", book.getTitle());
  }


  private void updateBook() {
    int bookNo = Prompt.inputInt("책 번호?");
    int index = bookList.indexOf(new Book(bookNo));
    if (index == -1) {
      System.out.println("없는 책입니다.");
      return;
    }

    Book book = bookList.get(index);

    book.setTitle(Prompt.input("이름(%s)?", book.getTitle()));
    System.out.println("변경 했습니다.");
  }

  private void deleteBook() {
    int bookNo = Prompt.inputInt("책 번호?");
    int index = bookList.indexOf(new Book(bookNo));
    if (index == -1) {
      System.out.println("없는 책입니다.");
      return;
    }

    Book deletedBook = bookList.remove(index);
    System.out.printf("'%s' 책을 삭제 했습니다.\n", deletedBook.getTitle());
  }
}
