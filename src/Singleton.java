


/**
 * Created by VdM on 18/05/2017.
 */

import java.sql.*;

public class Singleton {

    private static Connection conn = null;

    private Singleton(){}

    private Statement stmnt;

        public static Connection getConn() {

            if (conn == null) {
                try {
                    String url = "jdbc:mysql://127.0.0.1:8889/battleship";
                    String usr = "root";
                    String psw = "root";
                    String driver = "com.mysql.jdbc.Driver";
                    Class.forName(driver);
                    conn = DriverManager.getConnection(url, usr, psw);


                } catch (SQLException el) {

                    el.printStackTrace();

                } catch (ClassNotFoundException el) {

                    el.printStackTrace();
                }

            }
            return conn;
        }

}


