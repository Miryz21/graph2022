package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;

public class EdgeAmbiguity {
    HashMap<UUID, Boolean> visited = new HashMap<UUID, Boolean>();
    public void DFS(Graph g, UUID id){
        ArrayList<UUID> adjList = new ArrayList<UUID>();
        visited.put(id, true);
        for (Edge edge : g.getEdges()){
            if (edge.getFromV() == id && !visited.get(edge.getFromV())){
                adjList.add(edge.getFromV());
            }
            if (edge.getToV() == id && !visited.get(edge.getToV())){
                adjList.add(edge.getToV());
            }
        }
        for (UUID i : adjList){
            DFS(g, i);
        }
    }

    public ArrayList<UUID> compFind(HashMap<UUID, Boolean> mapa){
        ArrayList<UUID> res = new ArrayList<UUID>();
        for (var i : mapa.keySet()){
            if (mapa.get(i)){
                res.add(i);
            }
        }

        return res;
    }

    public ArrayList<ArrayList<UUID>> graphComps(Graph g){
        ArrayList<UUID> vertList = new ArrayList<UUID>();
        ArrayList<ArrayList<UUID>> res = new ArrayList<ArrayList<UUID>>();

        for (var v : g.getVertices().keySet()){
            vertList.add(v);
        }

        HashMap<UUID, Boolean> visited = new HashMap<UUID, Boolean>();

        while (vertList.size() > 0){
            DFS(g, vertList.get(0));
            res.add(compFind(visited));
            vertList.remove(0);
        }

        return res;
    }

    public ArrayList<ArrayList<UUID>> edgeCompFind(Graph g){
        ArrayList<ArrayList<UUID>> res = new ArrayList<ArrayList<UUID>>();
        ArrayList<Edge> bridges = new ArrayList<Edge>();
        ArrayList<Edge> edgeList = new ArrayList<Edge>(g.getEdges());
        ArrayList<Edge> temp = new ArrayList<Edge>(edgeList);

        for (var e : edgeList){
            temp.remove(e);
            Graph tempg = new Graph(g.getDirectType(), g.getVertices().size(), temp.size(), g.getVertices(), temp);
            if (graphComps(tempg).size() > graphComps(g).size()){
                bridges.add(e);
                res.addAll(graphComps(tempg));
            }
        }

        return res;
    }
}
