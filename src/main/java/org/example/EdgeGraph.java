package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;

public class EdgeGraph{
    public boolean isEdgeGraph(Graph g1, Graph g2) {
        //Проверяем количество рёбер в g1 и количество вершин в g2 (должны совпадать)
        if (g1.getEdgeCount() != g2.getVertexCount()){
            return false;
        }

        var verticesDeg = new ArrayList<Integer>();
        var edgesDeg = new ArrayList<Integer>();

        //список степеней вершин
        for (var i : g2.getVertices().keySet()){
            verticesDeg.add(g2.getVertices().get(i).getWeight());
        }

        //список "степеней" рёбер (сколько у каждого ребра смежных рёбер)
        for (var i : g1.getEdges()){
            int s = 0;
            s += g1.getVertices().get(i.getFromV()).getWeight() + g1.getVertices().get(i.getToV()).getWeight() - 2;
            edgesDeg.add(s);
        }

        Collections.sort(verticesDeg);
        Collections.sort(edgesDeg);

        //сравниваем эти списки (должны совпадать)
        return verticesDeg.equals(edgesDeg);
    }
}
