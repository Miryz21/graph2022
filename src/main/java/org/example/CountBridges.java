package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class CountBridges {

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
        var t = new HashSet<ArrayList<UUID>>(res);

        return new ArrayList<ArrayList<UUID>>(t);
    }

    public Integer bridgeCounter(Graph g) {

        ArrayList<ArrayList<UUID>> res = new ArrayList<ArrayList<UUID>>();
        ArrayList<Edge> bridges = new ArrayList<Edge>();
        ArrayList<Edge> edgeList = new ArrayList<Edge>(g.getEdges());

        for (var e : edgeList) {
            ArrayList<Edge> temp = new ArrayList<Edge>(edgeList);

            temp.remove(e);
            Graph tempg = new Graph(g.getDirectType(), g.getVertices().size(), temp.size(), g.getVertices(), temp);
            int i_1 = graphComps(tempg).size();
            int i_2 = graphComps(g).size();

            if (i_1 > i_2) {
                bridges.add(e);
                res.addAll(graphComps(tempg));
            }
        }

        return bridges.size();
    }
}
