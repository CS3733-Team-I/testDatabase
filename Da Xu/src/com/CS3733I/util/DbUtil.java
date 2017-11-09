package com.CS3733I.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

    public static void createTables(Connection con)throws Exception{
        String sql1 = "create table t_nodes(\n" +
                "  NodeID VARCHAR(10)PRIMARY KEY NOT NULL ,\n" +
                "  xcoord int NOT NULL,\n" +
                "  ycoord int NOT NULL,\n" +
                "  floor VARCHAR(2) NOT NULL,\n" +
                "  building VARCHAR(10) NOT NULL,\n" +
                "  nodeType VARCHAR(4) NOT NULL,\n" +
                "  longName VARCHAR(100) NOT NULL,\n" +
                "  shortName VARCHAR(25) NOT NULL,\n" +
                "  teamAssigned VARCHAR(6) NOT NULL\n" +
                ")";
        String sql2 = "create table t_edges(\n" +
                "  edgeID VARCHAR(21)PRIMARY KEY NOT NULL,\n" +
                "  startNode VARCHAR(10) NOT NULL,\n" +
                "  endNode VARCHAR(10) NOT NULL\n" +
                ")";
        PreparedStatement pstmt1 = con.prepareStatement(sql1);
        PreparedStatement pstmt2 = con.prepareStatement(sql2);

        pstmt1.execute();
        pstmt2.execute();
    }
}
