import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Connector {
    private String dbms = "derby";
    private String dbName = "testDB";
    private String userName = "jfparrick";
    private String password = "test123";
    private String urlString = "";

    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String serverName = "localhost";
    private int portNumber = 3306;

    private Connection con;

    private ArrayList<String[]> csvData = new ArrayList<>();
    private String csvFile = "";


    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection(
                "jdbc:" + this.dbms + ":" +
                        this.dbName +
                        ";create=true",
                connectionProps);
        System.out.println("Connected to database");
        con = conn;
        return conn;
    }

    public void createTable() throws SQLException {
        String createString =
                "CREATE TABLE " + dbName +
                        ".MAPNODES " +
                        "(NodeID varchar(16) NOT NULL, " +
                        "XCoord int NOT NULL, " +
                        "YCoord int NOT NULL, " +
                        "Floor varchar(8) NOT NULL, " +
                        "Building varchar(16) NOT NULL, " +
                        "NodeType varchar(8) NOT NULL, " +
                        "LongName varchar(32) NOT NULL, " +
                        "ShortName varchar(16) NOT NULL, " +
                        "TeamAssigned varchar(8) NOT NULL, " +
                        "PRIMARY KEY (NodeID))";

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            System.out.println("ERROR");
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void populateTable() throws SQLException {

    }

    public void addElement(String[] element)  throws SQLException {
        String addStmt = ""
        Statement stmt = null;
        if(element.length != 9) {
            System.out.println("Invalid number of values in one element");
            System.exit(1);
        }
        try {
            addStmt = "INSERT INTO " + dbName +
                    ".MAPNODES " +
                    "values(" + element[0] + ", " + Integer.parseInt(element[1]) +
                    ", " + Integer.parseInt(element[2]) + ", " + element[3] + ", " +
                    element[4] + ", " + element[5] + ", " + element[6] + ", " +
                    element[7] + ", " + element[8] + ")";

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            stmt.executeUpdate(addStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }


    public void readCSV() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                csvData.add(line.split(cvsSplitBy));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {
        Connector c = new Connector();
        c.getConnection();
    }
}
