package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;

public class K33Graph {
    public boolean hasK33Subgraph(Graph g) {
        for (var i : g.getVertices().keySet()) {
            if (g.getVertices().get(i).getWeight() >= 3) {
                for (var j : g.getVertices().keySet()) {
                    if (i != j && g.getVertices().get(j).getWeight() >= 3) {
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
