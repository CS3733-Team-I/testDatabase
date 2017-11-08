package com.CS3733I.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    private static String dbUserName = "dxu2";
    private static String dbPassword = "Xd980520";
    private static String dbUrl = "jdbc:derby://localhost:1527/db_test;create=true";

    public static Connection getCon()throws Exception{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    public static void closeCon(Connection con)throws Exception{
        if(con != null){
            con.close();
        }
    }
}
