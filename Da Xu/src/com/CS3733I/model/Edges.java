package com.CS3733I.model;

public class Edges {
    private String edgeID;
    private String startNode;
    private String endNode;

    public Edges() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Edges(String edgeID) {
        this.edgeID = edgeID;
    }

    public Edges(String edgeID, String startNode, String endNode) {
        this.edgeID = edgeID;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public String getEdgeID() {
        return edgeID;
    }

    public void setEdgeID(String edgeID) {
        this.edgeID = edgeID;
    }

    public String getStartNode() {
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public String getEndNode() {
        return endNode;
    }

    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }
}
