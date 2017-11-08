package com.CS3733I.objects;

import java.util.HashMap;

public class EdgeCollection {
    private static EdgeCollection instance = null;
    private HashMap<String, Edge> edgeHashMap = new HashMap<>();

    protected EdgeCollection() {

    }

    public void addEdge(Edge edge) {
        edgeHashMap.put(edge.getEdgeID(), edge);
    }

    public Edge getEdge(String id) {
        return edgeHashMap.get(id);
    }

    public static EdgeCollection getInstance() {
        if(instance == null) {
            instance = new EdgeCollection();
        }
        return instance;
    }
}
