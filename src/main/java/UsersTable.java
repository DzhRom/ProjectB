import jakarta.persistence.*;

import java.sql.*;


@Entity
public class UsersTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private final String URL = "jdbc:postgresql://localhost/ProjectB";
    private final String USER = "postgres";
    private final String PASSWORD = "170872";


    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void queryUsers(Connection conn) throws SQLException {
        try (
                PreparedStatement ps = conn.prepareStatement("select * from Users")
        ) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " "
                                    + rs.getString("firstName") + " "
                                    + rs.getString("lastName") + " "
                                    + rs.getInt("balance") + " "
                                    + rs.getString("login") + " "
                                    + rs.getString("password") + " "
                    );
                }
            }
        }
    }

    public void addUser(Connection conn, String firstName, String lastName, int balance, String login, String password) throws SQLException {
        try (
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users (firstname, lastname, balance, login, password) VALUES (?,?,?,?,?)")
        ) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, balance);
            ps.setString(4, login);
            ps.setString(5, password);
            int affectedRows = ps.executeUpdate();
        }
    }

    public int checkLogin(Connection conn, String login) throws SQLException {

        try (
                PreparedStatement ps = conn.prepareStatement("SELECT COUNT(login) FROM users WHERE login = ?");
        ) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    count += rs.getInt(1);
                }
                return count;
            }
        }
    }

    public void updateBalance(int idUser, int balance) throws SQLException {


        try (
                Connection conn = connect();
                PreparedStatement ps = conn.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
                ) {
            ps.setInt(1, balance);
            ps.setInt(2, idUser);
            int affectedRows = ps.executeUpdate();
        }
    }
}
