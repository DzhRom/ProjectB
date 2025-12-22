import java.sql.*;
import java.util.Scanner;

public class Authorization {
    private Connection connection = new UsersTable().connect();
    private String login;
    private int balance;
    private String password;
    private String name;


    public void authorizationUser() throws SQLException {
        this.login = inputLogin();
        this.password = inputPassword();
       /** try(
                PreparedStatement preparedStatement = connection.prepareStatement("select firstname, login, password, balance from users where login = ? and password = ?")
                ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                   /** System.out.println(resultSet.getString("firstname") + " "
                    + resultSet.getString("login") + " "
                    + resultSet.getString("password") + " "
                    + resultSet.getInt("balance"));
                    name = resultSet.getString("firstname");
                    balance = resultSet.getInt("balance");
                }
            }
        } */

       ResultSet rs = queryAuth(login, password);
       while (rs.next()) {
           this.name = rs.getString("firstname");
           this.balance = rs.getInt("balance");
       }

        System.out.println("Добро пожаловать " + name + "\nВаш баланс равен " + balance);

    }

    public ResultSet queryAuth(String login, String password) throws SQLException {

            PreparedStatement statement = connection.prepareStatement("select firstname, login, password, balance from users where login = ? and password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            return result;

    }

    public String inputLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваш логин : ");
        return scanner.nextLine();
    }

    public String inputPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваш пароль: ");
        return scanner.nextLine();
    }

}
