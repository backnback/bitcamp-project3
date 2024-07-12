package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UserCommand extends AbstractCommand {

  private List<User> userList;
  private String[] menus = {"등록", "목록", "조회", "변경", "삭제"};

  public UserCommand(String menuTitle, List<User> list) {
    super(menuTitle);
    this.userList = list;
  }

  @Override
  protected void processMenu(String menuName) {
    System.out.printf("[%s]\n", menuName);
    switch (menuName) {
      case "등록":
        this.addUser();
        break;
      case "목록":
        this.listUser();
        break;
      case "조회":
        this.viewUser();
        break;
      case "변경":
        this.updateUser();
        break;
      case "삭제":
        this.deleteUser();
        break;
    }
  }
@Override
protected String[] getMenus() {
  return menus;
}


  private void addUser() {
    User user = new User();
    user.setName(Prompt.input("이름?"));
    user.setRegistrationDate(LocalDate.now());
    user.setNo(User.getNextSeqNo());
    userList.add(user);
  }

  private void listUser() {
    System.out.println("번호\t\t이름\t\t날짜");
    for (User user : userList) {
      System.out.printf("%d\t\t\t%s\t%s\n", user.getNo(), user.getName(),user.getRegistrationDate());
    }
  }




  private void viewUser() {
    int userNo = Prompt.inputInt("회원번호?");
    int index = userList.indexOf(new User(userNo));
    if (index == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }

    User user = userList.get(index);

    System.out.printf("이름: %s\n", user.getName());
  }

  private void updateUser() {
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

  private void deleteUser() {
    int userNo = Prompt.inputInt("회원번호?");
    int index = userList.indexOf(new User(userNo));
    if (index == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }

    User deletedUser = userList.remove(index);
    System.out.printf("'%s' 회원을 삭제 했습니다.\n", deletedUser.getName());
  }
}


