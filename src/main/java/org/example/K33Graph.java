package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;

public class K33Graph {
    public Integer VertexDegree(Vertex v, Graph g){
        int i = 0;
        for(var e: g.getEdges()){
            if(e.getFromV().equals(v.getId()) || e.getToV().equals(v.getId())){
                i++;
            }
        }
        return i;
    }

    public boolean hasK33Subgraph(Graph g) {
        for (var i : g.getVertices().keySet()) {
            if (VertexDegree(g.getVertices().get(i), g) >= 3) {
                for (var j : g.getVertices().keySet()) {
                    if (i != j && VertexDegree(g.getVertices().get(j), g) >= 3) {
                        int count = 0;
                        var tempi = new HashSet<Edge>();
                        var tempj = new HashSet<Edge>();
                        for (var k : g.getEdges()){
                            if (k.getFromV() == i || k.getToV() == i){
                                tempi.add(k);
                            }
                        }

                        for (var k : g.getEdges()){
                            if (k.getToV() == j){
                                tempj.add(k);
                            }
                        }

                        for (Edge edge : tempi) {
                            if (edge.getToV() != j) {
                                count += tempj.stream().anyMatch((e) -> e.getToV() == j) ? 1 : 0;
                            }
                        }

                        if (count >= 3) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
