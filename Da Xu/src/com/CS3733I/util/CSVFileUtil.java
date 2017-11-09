package com.CS3733I.util;

import com.CS3733I.dao.EdgesDao;
import com.CS3733I.dao.NodesDao;
import com.CS3733I.model.Edges;
import com.CS3733I.model.Nodes;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CSVFileUtil {
    public void readNodesCSV(String path) throws Exception{
        File file = new File(path);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        bReader.readLine();

        DbUtil dbUtil = new DbUtil();
        NodesDao nodesDao = new NodesDao();

        Connection con = null;
        try {
            con = dbUtil.getCon();
            while ((line = bReader.readLine()) != null) {
                    String[] elements = line.split(",");
                    Nodes nodes = new Nodes(elements[0].trim(), Integer.parseInt(elements[1].trim()), Integer.parseInt(elements[2].trim()), elements[3].trim(),
                            elements[4].trim(), elements[5].trim(), elements[6].trim(), elements[7].trim(), elements[8].trim());
                    nodesDao.insertNodes(con, nodes);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void writeNodesCSV(String path) throws Exception{

        File csvFile = new File(path);
        FileWriter write = new FileWriter(csvFile, true);
        BufferedWriter bWriter = new BufferedWriter(write);

        DbUtil dbUtil = new DbUtil();
        Connection con = null;
        bWriter.write("nodeID,xcoord,ycoord,floor,building,nodeType,longName,shortName,teamAssigned");
        bWriter.newLine();
        try{
            con = dbUtil.getCon();
            String sql = "select * from T_NODES";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                bWriter.write(rs.getString("NodeID") + ",");
                bWriter.write(rs.getInt("xcoord") + ",");
                bWriter.write(rs.getInt("ycoord") + ",");
                bWriter.write(rs.getString("floor") + ",");
                bWriter.write(rs.getString("building") + ",");
                bWriter.write(rs.getString("nodeType") + ",");
                bWriter.write(rs.getString("longName") + ",");
                bWriter.write(rs.getString("shortName") + ",");
                bWriter.write(rs.getString("teamAssigned"));
                bWriter.newLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            bWriter.flush();
            write.close();
            bWriter.close();
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void readEdgesCSV(String path) throws Exception{
        File file = new File(path);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        bReader.readLine();

        DbUtil dbUtil = new DbUtil();
        EdgesDao edgesDao = new EdgesDao();
        Connection con = null;
        try {
            con = dbUtil.getCon();
            while ((line = bReader.readLine()) != null) {
                String[] elements = line.split(",");
                Edges edges= new Edges(elements[0].trim(), elements[1].trim(), elements[2].trim());
                edgesDao.insertEdges(con, edges);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeEdgesCSV(String path) throws Exception {

        File csvFile = new File(path);
        FileWriter write = new FileWriter(csvFile, true);
        BufferedWriter bWriter = new BufferedWriter(write);

        DbUtil dbUtil = new DbUtil();
        Connection con = null;
        bWriter.write("edgeID, startNode, endNode");
        bWriter.newLine();
        try {
            con = dbUtil.getCon();
            String sql = "select * from T_EDGES";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bWriter.write(rs.getString("edgeID") + ",");
                bWriter.write(rs.getString("startNode") + ",");
                bWriter.write(rs.getString("endNode") + ",");

                bWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bWriter.flush();
            write.close();
            bWriter.close();
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


