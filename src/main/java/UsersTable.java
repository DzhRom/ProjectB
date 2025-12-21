import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
            conn = DriverManager.getConnection(URL, USER, PASSWORD); System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void queryUsers(Connection conn) throws SQLException {
        try(
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

    public void addUser(Connection conn, String firstName, String lastName, int balance) throws SQLException {
        try (
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users (firstname, lastname, balance) VALUES (?,?,?)")
                ) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, balance);
            int affectedRows = ps.executeUpdate();
        }
    }

}
