package com.CS3733I.dao;

import com.CS3733I.model.Edges;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public Edges selectEdges(Connection con, String edgeID)throws Exception{
        Edges edges = new Edges(edgeID);
        String sql = "select * from T_EDGES where edgesID = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, edgeID);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            edges.setStartNode(rs.getString("startNode"));
            edges.setEndNode(rs.getString("endNode"));
        }
        return edges;
    }
}
