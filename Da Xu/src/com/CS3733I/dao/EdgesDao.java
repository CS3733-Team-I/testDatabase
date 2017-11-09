package com.CS3733I.dao;

import com.CS3733I.model.Edges;
import com.sun.javafx.geom.Edge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EdgesDao {
    public void insertEdges(Connection con, Edges edges)throws Exception{
        String sql = "insert into T_EDGES values(?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, edges.getEdgeID());
        pstmt.setString(2, edges.getStartNode());
        pstmt.setString(3, edges.getEndNode());
        pstmt.execute();
    }

    public void updateEdges(Connection con, Edges edges)throws Exception{
        String sql = "update T_EDGES set startNode=?, endNode=? where edgeID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, edges.getStartNode());
        pstmt.setString(2, edges.getEndNode());
        pstmt.setString(3, edges.getEdgeID());
        pstmt.executeUpdate();
    }

    public ArrayList<Edges> selectEdges(Connection con, ArrayList<Edges> edgesList)throws Exception{
        Edges edges;
        String sql = "select * from T_EDGES";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            edges = new Edges();
            edges.setEdgeID(rs.getString("edgeID"));
            edges.setStartNode(rs.getString("startNode"));
            edges.setEndNode(rs.getString("endNode"));
            edgesList.add(edges);
        }
        return edgesList;
    }

    public void deleteEdges(Connection con, Edges edges)throws Exception{
        String sql = "delete from t_edges where edgeID = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, edges.getEdgeID());
        pstmt.execute();
    }
}
