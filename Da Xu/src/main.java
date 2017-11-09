import com.CS3733I.dao.EdgesDao;
import com.CS3733I.dao.NodesDao;
import com.CS3733I.model.Edges;
import com.CS3733I.model.Nodes;
import com.CS3733I.util.CSVFileUtil;
import com.CS3733I.util.DbUtil;
import org.apache.derby.impl.store.raw.log.Scan;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        CSVFileUtil csvFileUtil = new CSVFileUtil();
        DbUtil dbUtil = new DbUtil();
        EdgesDao edgesDao = new EdgesDao();
        NodesDao nodesDao = new NodesDao();
        ArrayList<Nodes> nodesList = new ArrayList<Nodes>();
        ArrayList<Edges> edgesList = new ArrayList<Edges>();

        String inPath;
        Connection con = null;
        try {
            con = dbUtil.getCon();
            System.out.println("Successfully connected to database!\n");

            dbUtil.createTables(con);
            System.out.println("Tables are created.");

            System.out.println("Please select: 1--Nodes Operation  2--Edges Operation");
            Scanner choiceNumScanner = new Scanner(System.in);
            int choiceNum = choiceNumScanner.nextInt();

            switch (choiceNum) {
                case 1:
                    System.out.println("Enter a path to read the CSV file: ");
                    Scanner inputScanner = new Scanner(System.in);
                    inPath = inputScanner.next();
                    csvFileUtil.readNodesCSV(inPath);
                    nodesDao.selectNodes(con, nodesList);
                    //System.out.println(nodesList.get(0).getNodeID());


                    System.out.println("Import is finished. Try to update the database a little bit.");
                    System.out.println("Please enter an node ID:");
                    Scanner nodeIDScanner = new Scanner(System.in);
                    String nodeID = nodeIDScanner.next();

                    System.out.println("Please enter a x-coord:");
                    Scanner xcoordScanner = new Scanner(System.in);
                    int xcoord = xcoordScanner.nextInt();

                    System.out.println("Please enter a y-coord:");
                    Scanner ycoordScanner = new Scanner(System.in);
                    int ycoord = ycoordScanner.nextInt();

                    System.out.println("Please enter a floor:");
                    Scanner floorScanner = new Scanner(System.in);
                    String floor = floorScanner.next();

                    System.out.println("Please enter a building:");
                    Scanner buildingScanner = new Scanner(System.in);
                    String building = buildingScanner.next();

                    System.out.println("Please enter a node type:");
                    Scanner nodeTypeScanner = new Scanner(System.in);
                    String nodeType = nodeTypeScanner.next();

                    System.out.println("Please enter a long name:");
                    Scanner longNameScanner = new Scanner(System.in);
                    String longName = longNameScanner.next();

                    System.out.println("Please enter a short name:");
                    Scanner shortNameScanner = new Scanner(System.in);
                    String shortName = shortNameScanner.next();

                    System.out.println("Please enter a team assigned:");
                    Scanner teamAssignedScanner = new Scanner(System.in);
                    String teamAssigned = teamAssignedScanner.next();

                    Nodes nodes = new Nodes(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned);
                    nodesDao.updateNodes(con, nodes);

                    System.out.println("Update complete.");

                    System.out.println("Please enter a path that you want to export the CSV file:");

                    Scanner pathScanner = new Scanner(System.in);
                    String exportPath = pathScanner.next();
                    csvFileUtil.writeNodesCSV(exportPath);

                    System.out.println("Mission complete!");
                    break;

                case 2:
                    System.out.println("Enter a path to read the CSV file: ");
                    Scanner pathScanner2 = new Scanner(System.in);
                    inPath = pathScanner2.next();
                    csvFileUtil.readEdgesCSV(inPath);
                    edgesDao.selectEdges(con, edgesList);
                    //System.out.println(edgesList.get(0).getEdgeID());

                    System.out.println("Import is finished. Try to update the database a little bit.");
                    System.out.println("Please enter an edge ID:");
                    Scanner edgeIDScanner = new Scanner(System.in);
                    String edgeID = edgeIDScanner.next();

                    System.out.println("Enter the start node you want to modify:");
                    Scanner startNodeScanner = new Scanner(System.in);
                    String startNode = startNodeScanner.next();

                    System.out.println("Enter the end node you want to modify:");
                    Scanner endNodeScanner = new Scanner(System.in);
                    String endNode = endNodeScanner.next();

                    Edges edges = new Edges(edgeID, startNode, endNode);
                    edgesDao.updateEdges(con, edges);

                    System.out.println("Update Complete.");
                    System.out.println("Please enter a path that you want to export the CSV file:");

                    Scanner pathScanner3 = new Scanner(System.in);
                    String exportPath2 = pathScanner3.next();
                    csvFileUtil.writeEdgesCSV(exportPath2);

                    System.out.println("Mission complete!");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try{
                dbUtil.closeCon(con);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
