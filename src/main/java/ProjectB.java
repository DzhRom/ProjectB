import java.sql.SQLException;

public class ProjectB {
    public static void main(String[] args) throws Exception {

        UsersTable ut = new UsersTable();
        ut.queryUsers(ut.connect());
       // User us =new User();
       // ut.queryUsers(ut.connect());
    }
}
