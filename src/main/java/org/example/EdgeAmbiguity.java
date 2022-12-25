package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;

public class EdgeAmbiguity {
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
        ArrayList<Edge> temp = new ArrayList<Edge>(edgeList);

        for (var e : edgeList){
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
        var l = new ArrayList<ArrayList<UUID>>(t);

        String label = "0";
        for (var i: l){
            for (var j: i){
                g.getVertices().get(j).setLabel(label);
            }
            label+=1;
        }

        return res.size();
    }
}
