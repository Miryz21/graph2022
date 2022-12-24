package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

    public class VertexAmbiguiltyTest {
        @Test
        void count_bridges1() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2_2.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(2);
        }
        @Test
        void count_bridges2() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph1.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(4);
        }
    }

