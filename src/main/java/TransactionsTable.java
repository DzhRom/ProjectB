import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ConnectionBD connBD =  new ConnectionBD();
    private int id;

    public void addTransaction ( int idUser, String transaction, int amount, int balance) throws SQLException {
        try (
                Connection conn = connBD.connection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO transactions (userid, transactions, amount,  balance) VALUES (?, ?, ?, ?);")
                ) {
            ps.setInt(1, idUser);
            ps.setString(2, transaction);
            ps.setInt(3, amount);
            //ps.setDate(4, date);
            ps.setInt(4, balance);
            int affectedRows = ps.executeUpdate();
        }
    }


}
