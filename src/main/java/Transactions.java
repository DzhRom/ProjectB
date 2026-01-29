import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Transactions {
    private int id_user;
    private String transaction;
    private int amount;
    private String date;
    private int balance;
    private TransactionsTable transactionsTable = new TransactionsTable();


    public int setAmount() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите количество: ");
        return input.nextInt();
    }

    public Transactions(Authorization authorization) {
        id_user = authorization.getIdUser();
        balance = authorization.getBalance();

    }

    public void checkIdUser(){
        System.out.println(id_user);
        System.out.println(balance);
    }

    public void withdrawCash() throws SQLException {
        amount = setAmount();
        if(amount > balance){
            System.out.println("Недостаточно средств.");
        } else {
            balance -= amount;
        }
        transactionsTable.addTransaction(id_user, "Снятие наличных", amount, balance);
        UsersTable usersTable = new UsersTable();
        usersTable.updateBalance(balance, id_user);
        System.out.println(balance);
    }

    public void depositCash() throws SQLException {
        amount = setAmount();
        balance += amount;
        transactionsTable.addTransaction(id_user, "Внесение наличных", amount, balance);
        UsersTable usersTable = new UsersTable();
        usersTable.updateBalance(id_user ,balance);
        System.out.println(balance);
    }



}
