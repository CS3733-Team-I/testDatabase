package com.CS3733I.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String dbUserName = "localkiosk";
    private static String dbPassword = "thisisapassword";
    private static String dbUrl = "jdbc:derby://localhost:1527/db_test;create=true";

    public static Connection getCon()throws SQLException{
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    public static void closeCon(Connection con) throws SQLException{
        if(con != null){
            con.close();
        }
    }
}
