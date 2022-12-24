package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;

public class VertexAmbiguity {
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

    public ArrayList<ArrayList<UUID>> blocksFind(Graph g){
        ArrayList<UUID> vertList = new ArrayList<UUID>();
        ArrayList<UUID> temp = new ArrayList<UUID>(vertList);
        ArrayList<UUID> cut = new ArrayList<UUID>();
        ArrayList<ArrayList<UUID>> res = new ArrayList<ArrayList<UUID>>();

        for (var v : g.getVertices().keySet()){
            vertList.add(v);
        }

        for (var v : vertList){
            temp = vertList;
            temp.remove(v);
            var edgesList = new ArrayList<Edge>(g.getEdges());
            for (var e : edgesList){
                if (e.getFromV() == v || e.getToV() == v){
                    edgesList.remove(e);
                }
            }
            var tempVertex = new HashMap<UUID, Vertex>(g.getVertices());
            tempVertex.remove(v);
            Graph tempg = new Graph(g.getDirectType(), temp.size(), edgesList.size(), tempVertex, edgesList);

            if (graphComps(tempg).size() > graphComps(g).size()){
                cut.add(v);
                res.addAll(graphComps(tempg));
            }
        }

        return res;
    }
}
