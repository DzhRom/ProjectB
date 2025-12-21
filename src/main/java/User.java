import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private String firstName;
    private String lastName;
    private int balance;
    private String login;
    private String password;



    public User(String firstName, String lastName, int balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public User() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя: ");
        this.firstName = sc.nextLine();
        System.out.println("Введите фамилию: ");
        this.lastName = sc.nextLine();
        System.out.println("Введите баланс: ");
        this.balance = sc.nextInt();
        UsersTable ut = new UsersTable();
        ut.addUser(ut.connect(), firstName, lastName, balance);
    }

}
