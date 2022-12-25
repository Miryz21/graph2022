package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
public class EdgeAmbiguilityTest {

    @Test
    void Edge1() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/17_graph1.txt"));
        EdgeAmbiguity countEdges = new EdgeAmbiguity();
        assertThat(countEdges.edgeCompFind(testGraph)).isEqualTo(1);
    }
    @Test
    void Edge2() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/17_graph2.txt"));
        EdgeAmbiguity countEdges = new EdgeAmbiguity();
        assertThat(countEdges.edgeCompFind(testGraph)).isEqualTo(2);
    }
    @Test
    void Edge3() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/17_graph3.txt"));
        EdgeAmbiguity countEdges = new EdgeAmbiguity();
        assertThat(countEdges.edgeCompFind(testGraph)).isEqualTo(3);
    }
    @Test
    void Edge4() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/17_graph4.txt"));
        EdgeAmbiguity countEdges = new EdgeAmbiguity();
        assertThat(countEdges.edgeCompFind(testGraph)).isEqualTo(4);
    }
    @Test
    void Edge5() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/17_graph5.txt"));
        EdgeAmbiguity countEdges = new EdgeAmbiguity();
        assertThat(countEdges.edgeCompFind(testGraph)).isEqualTo(4);
    }
    @Test
    void Edge6() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/17_graph6.txt"));
        EdgeAmbiguity countEdges = new EdgeAmbiguity();
        assertThat(countEdges.edgeCompFind(testGraph)).isEqualTo(4);
    }

}
