package com.CS3733I.dao;

import com.CS3733I.model.Nodes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Nodes> selectNodes(Connection con, ArrayList<Nodes> nodesList)throws Exception{
        Nodes nodes;
        String sql = "select * from T_nodes";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            nodes = new Nodes();
            nodes.setNodeID(rs.getString("nodeID"));
            nodes.setXcoord(rs.getInt("xcoord"));
            nodes.setYcoord(rs.getInt("ycoord"));
            nodes.setFloor(rs.getString("floor"));
            nodes.setBuilding(rs.getString("building"));
            nodes.setNodeType(rs.getString("nodeType"));
            nodes.setLongName(rs.getString("longName"));
            nodes.setShortName(rs.getString("shortName"));
            nodes.setTeamAssigned(rs.getString("teamAssigned"));
            nodesList.add(nodes);
        }
        return nodesList;
    }

    public void updateNodes(Connection con, Nodes nodes)throws Exception{
        String sql = "update T_Nodes set xcoord=?, ycoord=?, floor=?, building=?, nodeType=?, longName=?, shortName=?, teamAssigned=? where nodeID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, nodes.getXcoord());
        pstmt.setInt(2, nodes.getYcoord());
        pstmt.setString(3, nodes.getFloor());
        pstmt.setString(4, nodes.getBuilding());
        pstmt.setString(5, nodes.getNodeType());
        pstmt.setString(6, nodes.getLongName());
        pstmt.setString(7, nodes.getShortName());
        pstmt.setString(8, nodes.getTeamAssigned());
        pstmt.setString(9, nodes.getNodeID());
        pstmt.executeUpdate();
    }

    public void deleteNodes(Connection con, Nodes nodes)throws Exception{
        String sql = "delete from T_nodes where nodesID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, nodes.getNodeID());
        pstmt.execute();
    }
}
