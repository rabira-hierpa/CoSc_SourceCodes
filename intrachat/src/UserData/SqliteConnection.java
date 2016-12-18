package UserData;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * IntraChat
 *
 * @ 00 24
 * Created by rabira on 12/19/16.
 */
public class SqliteConnection {
    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:user_db.sqlite");
            return con;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
