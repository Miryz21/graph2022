package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class VertexAmbiguity {
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
            if (edge.getFromV().equals(id) && !(visited.containsKey(edge.getFromV()))) {
                adjList.add(edge.getFromV());
            }
            if (edge.getToV().equals(id) && !(visited.containsKey(edge.getToV()))) {
                adjList.add(edge.getToV());
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

        return new ArrayList<ArrayList<UUID>>(t);
    }

    public Integer blocksFind(Graph g) {
        int count;
        ArrayList<UUID> cut = new ArrayList<UUID>();

        ArrayList<UUID> vertList = new ArrayList<UUID>(g.getVertices().keySet());

        for (var v : vertList) {

            ArrayList<UUID> temp = new ArrayList<UUID>(vertList);

            var edgesList = new ArrayList<Edge>(g.getEdges());
            var temp_e = new ArrayList<Edge>(g.getEdges());
            for (var e : edgesList) {
                if (e.getFromV().equals(v) || e.getToV().equals(v)) {
                    temp_e.remove(e);
                }
            }
            temp.remove(v);
            var tempVertex = new HashMap<UUID, Vertex>(g.getVertices());
            tempVertex.remove(v);
            Graph tempg = new Graph(g.getDirectType(), temp.size(), temp_e.size(), tempVertex, temp_e);
            int i_1 = graphComps(tempg).size();
            int i_2 = graphComps(g).size();
            if (graphComps(tempg).size() > graphComps(g).size()) {
                cut.add(v);
            }

        }
        var edgesList = new ArrayList<Edge>();
        for(var e: g.getEdges()) {
            var edge = new Edge(e.getFromV(), e.getToV(), Color.gray, null, null);

            edgesList.add(edge);
        }

        var tempEdgeList = new ArrayList<Edge>(edgesList);


        var tempVertex = new HashMap<UUID, Vertex>(g.getVertices());
        ArrayList<UUID> temp = new ArrayList<UUID>(vertList);


        for (var v : cut) {
            for (var e : edgesList) {
                if (e.getFromV().equals(v) || e.getToV().equals(v)) {
                    tempEdgeList.remove(e);
                }
            }

            temp.remove(v);
            tempVertex.remove(v);

        }
        Graph tempg = new Graph(g.getDirectType(), temp.size(), tempEdgeList.size(), tempVertex, tempEdgeList);
        int i_1 = graphComps(tempg).size();
        int i_2 = graphComps(g).size();

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


        return (new ArrayList<ArrayList<UUID>>(t)).size() == 0 ? 1 : (new ArrayList<ArrayList<UUID>>(t)).size();
    }
}
