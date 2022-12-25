package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;

public class EdgeAmbiguity {
    public void get_labels(Graph g, UUID id, HashMap<UUID, Boolean> visited, HashMap<UUID, String> labels) {
        ArrayList<UUID> adjList = new ArrayList<UUID>();
        visited.put(id, true);
        labels.put(id, g.getVertices().get(id).getLabel());
        for (Edge edge : g.getEdges()) {

            if (edge.getFromV().equals(id) && !(visited.containsKey(edge.getToV()))) {
                adjList.add(edge.getToV());
            }
            if (edge.getToV().equals(id) && !(visited.containsKey(edge.getFromV()))) {
                adjList.add(edge.getFromV());
            }
        }
        for (UUID i : adjList) {
            get_labels(g, i, visited, labels);
        }
    }
    public void DFS(Graph g, UUID id, HashMap<UUID, Boolean> visited) {
        ArrayList<UUID> adjList = new ArrayList<UUID>();
        visited.put(id, true);
        for (Edge edge : g.getEdges()) {

            if (edge.getFromV().equals(id) && !(visited.containsKey(edge.getToV()))) {
                adjList.add(edge.getToV());
            }
            if (edge.getToV().equals(id) && !(visited.containsKey(edge.getFromV()))) {
                adjList.add(edge.getFromV());
            }
        }
        for (UUID i : adjList) {
            DFS(g, i, visited);
        }
    }

    public ArrayList<UUID> compFind(HashMap<UUID, Boolean> visited) {

        ArrayList<UUID> res = new ArrayList<UUID>(visited.keySet());
        visited.clear();
        return res;
    }

    public ArrayList<ArrayList<UUID>> graphComps(Graph g) {
        ArrayList<ArrayList<UUID>> res = new ArrayList<ArrayList<UUID>>();

        ArrayList<UUID> vertList = new ArrayList<UUID>(g.getVertices().keySet());

        HashMap<UUID, Boolean> visited = new HashMap<UUID, Boolean>();

        for (UUID uuid : vertList) {
            DFS(g, uuid, visited);
            res.add(compFind(visited));

        }
        for (var i : res) {
            Collections.sort(i);
        }
        var t = new HashSet<ArrayList<UUID>>(res);
        var l = new ArrayList<ArrayList<UUID>>(t);
        return l;
    }

    public Integer edgeCompFind(Graph g){
        ArrayList<Edge> bridges = new ArrayList<Edge>();
        ArrayList<Edge> edgeList = new ArrayList<Edge>(g.getEdges());
        ArrayList<Edge> edgeList2 = new ArrayList<Edge>(g.getEdges());

        for (var e : edgeList){
            ArrayList<Edge> temp = new ArrayList<Edge>(edgeList);

            temp.remove(e);
            Graph tempg = new Graph(g.getDirectType(), g.getVertices().size(), temp.size(), g.getVertices(), temp);
            if (graphComps(tempg).size() > graphComps(g).size()){
                bridges.add(e);
            }
        }
        for(var e: bridges){
                edgeList2.remove(e);
            }

        Graph tempg = new Graph(g.getDirectType(), g.getVertices().size(), edgeList2.size(), g.getVertices(), edgeList2);

        ArrayList<ArrayList<UUID>> res = new ArrayList<ArrayList<UUID>>(graphComps(tempg));

        for (var i : res) {
            Collections.sort(i);
        }
        var t = new HashSet<ArrayList<UUID>>(res);
        var result = new ArrayList<ArrayList<UUID>>(t);

        HashMap<UUID, String> labels = new HashMap<UUID, String>();
        HashMap<UUID, Boolean> visited = new HashMap<UUID, Boolean>();
        ArrayList<UUID> vertList_1 = new ArrayList<UUID>(g.getVertices().keySet());

        for (var i : vertList_1) {
            get_labels(g, i, visited, labels);
        }
        ArrayList<String> unique_labels = new ArrayList<String>();
        for ( var p : labels.values()){
            if (!unique_labels.contains(p)){
                unique_labels.add(p);
            }
        }

        ArrayList<ArrayList<UUID>> res_1 = new ArrayList<ArrayList<UUID>>();
        int k = 0;

        for(var i : unique_labels) {
            res_1.add(new ArrayList<UUID>());
            for (var key : labels.keySet()) {
                if (labels.get(key).equals(i)) {
                    res_1.get(k).add(key);
                }
            }
            k+=1;

        }

        for (var i : res_1) {
            Collections.sort(i);
        }
        var z = new HashSet<ArrayList<UUID>>(res_1);

        var result_1 = new ArrayList<ArrayList<UUID>>(z);


//        return result.equals(result_1);
        return result.size();
    }
}
