package Pertemuan10.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySqlConnection {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/biodata";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "";

    private static Pertemuan10.db.MySqlConnection instance;

    public static Pertemuan10.db.MySqlConnection getInstance() {
        if (instance == null) {
            instance = new Pertemuan10.db.MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
