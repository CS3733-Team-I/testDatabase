package com.CS3733I.dao;

import com.CS3733I.model.Edges;
import com.CS3733I.model.Nodes;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class NodesDao {

    public void insertNodes(Connection con, Nodes nodes)throws Exception{
        String sql = "insert into T_NODES values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, nodes.getNodeID());
        pstmt.setInt(2, nodes.getXcoord());
        pstmt.setInt(3, nodes.getYcoord());
        pstmt.setString(4, nodes.getFloor());
        pstmt.setString(5, nodes.getBuilding());
        pstmt.setString(6, nodes.getNodeType());
        pstmt.setString(7, nodes.getLongName());
        pstmt.setString(8, nodes.getShortName());
        pstmt.setString(9, nodes.getTeamAssigned());
        pstmt.execute();
    }

    public void updateNodes(Connection con, Nodes nodes)throws Exception{
        String sql = "update T_EDGES set xcoord=?, ycoord=?, floor=?, building=?, nodeType=?, longName=?, shortName=?, teamAssigned=? where edgeID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, nodes.getXcoord());
        pstmt.setInt(2, nodes.getYcoord());
        pstmt.setString(3, nodes.getFloor());
        pstmt.setString(4, nodes.getBuilding());
        pstmt.setString(5, nodes.getNodeType());
        pstmt.setString(6, nodes.getLongName());
        pstmt.setString(7, nodes.getShortName());
        pstmt.setString(8, nodes.getTeamAssigned());
        pstmt.executeUpdate();
    }


}
