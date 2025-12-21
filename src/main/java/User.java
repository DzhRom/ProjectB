import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private String firstName;
    private String lastName;
    private int balance;
    private String login;
    private String password;
    private UsersTable usersTable;
    Scanner sc = new Scanner(System.in);



    public User(String firstName, String lastName, int balance, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.login = login;
        this.password = password;
    }

    public User() throws SQLException {
        usersTable = new UsersTable();
        System.out.print("Введите имя: ");
        this.firstName = sc.nextLine();
        System.out.print("Введите фамилию: ");
        this.lastName = sc.nextLine();
        this.login = addLogin();
        System.out.print("Введите пароль: ");
        this.password = sc.nextLine();
        System.out.print("Введите баланс: ");
        this.balance = sc.nextInt();

        usersTable.addUser(usersTable.connect(), firstName, lastName, balance, login, password);
    }

    public String addLogin() throws SQLException {
        System.out.print("Введите логин: ");
        String log = sc.nextLine();
        if (usersTable.checkLogin(usersTable.connect(), log) == 0) {
             login = log;
        } else {
            System.out.print("Логин уже занят. ");
            addLogin();
        }
        return login;
    }

}
