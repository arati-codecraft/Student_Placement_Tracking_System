package org.placepro.DBconfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {
        try {
            // Ensure only one connection is reused
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_placement_tracking_system",
                    "root",
                    "root"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
