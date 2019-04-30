package ru.myfunc.dangerchat.repository;

import java.sql.*;

public class DataRepository {
    public static void temp() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/dangerchar?" +
                            "user=root&password=xtremal&useUnicode=true&useJDBCCompliantTimezoneShift=true&" +
                            "useLegacyDatetimeCode=false&serverTimezone=UTC");
            stmt = conn.createStatement();
            if (stmt.execute("SELECT Id, Name FROM tblUser")) {
                rs = stmt.getResultSet();
                while(rs.next()){
                    System.out.printf("%s, %s\n", rs.getString("Id"), rs.getString("Name"));
                }

            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
