package com.CS3733I.connection;

import com.CS3733I.objects.Edge;
import com.CS3733I.objects.EdgeCollection;
import com.CS3733I.objects.Node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {


    public static void insertEdge(Connection conn, Edge edge) throws SQLException {
        String sql = "insert into T_EDGES values(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, edge.getEdgeID());
        pstmt.setString(2, edge.getNode1().getNodeID());
        pstmt.setString(3, edge.getNode2().getNodeID());
        pstmt.execute();
    }

    public static void updateEdge(Connection conn, Edge edge) throws SQLException {
        String sql = "update T_EDGES set startNode=?, endNode=? where edgeID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, edge.getNode1().getNodeID());
        pstmt.setString(2, edge.getNode2().getNodeID());
        pstmt.setString(3, edge.getEdgeID());
        pstmt.executeUpdate();
    }

    public static Edge selectEdge(Connection conn, String edgeID) throws SQLException {
        String sql = "select * from T_EDGES where edgesID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, edgeID);
        ResultSet rs = pstmt.executeQuery();
        Edge edge = EdgeCollection.getInstance().getEdge(edgeID);
        if(rs.next()) {
            edge.setNode1(rs.getString("startNode"));
            edge.setNode2(rs.getString("endNode"));
        }
        return edge;
    }

    public static void insertNode(Connection conn, Node node)throws SQLException{
        String sql = "insert into T_NODES values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, node.getNodeID());
        pstmt.setInt(2, node.getXcoord());
        pstmt.setInt(3, node.getYcoord());
        pstmt.setString(4, node.getFloor());
        pstmt.setString(5, node.getBuilding());
        pstmt.setString(6, node.getNodeType());
        pstmt.setString(7, node.getLongName());
        pstmt.setString(8, node.getShortName());
        pstmt.setString(9, node.getTeamAssigned());
        pstmt.execute();
    }

    public void updateNode(Connection conn, Node node)throws SQLException{
        String sql = "update T_EDGES set xcoord=?, ycoord=?, floor=?, building=?, nodeType=?, longName=?, shortName=?, teamAssigned=? where edgeID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, node.getXcoord());
        pstmt.setInt(2, node.getYcoord());
        pstmt.setString(3, node.getFloor());
        pstmt.setString(4, node.getBuilding());
        pstmt.setString(5, node.getNodeType());
        pstmt.setString(6, node.getLongName());
        pstmt.setString(7, node.getShortName());
        pstmt.setString(8, node.getTeamAssigned());
        pstmt.executeUpdate();
    }
}
