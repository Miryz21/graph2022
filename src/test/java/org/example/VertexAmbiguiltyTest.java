package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

    public class VertexAmbiguiltyTest {
        @Test
        void VertexAmbiguility_1() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph1.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(2);
        }

        @Test
        void VertexAmbiguility_2() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph2.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(3);
        }
        @Test
        void VertexAmbiguility_3() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph3.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(1);
        }
        @Test
        void VertexAmbiguility_4() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph4.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(3);
        }
        @Test
        void VertexAmbiguility_5() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph5.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(1);
        }
        @Test
        void VertexAmbiguility_6() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph6.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(2);
        }
        @Test
        void VertexAmbiguility_7() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph7.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(1);
        }
        @Test
        void VertexAmbiguility_8() throws FileNotFoundException {
            Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/16_graph8.txt"));
            VertexAmbiguity countVertex = new VertexAmbiguity();
            assertThat(countVertex.blocksFind(testGraph)).isEqualTo(3);
        }
    }

