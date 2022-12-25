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
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/17graph_1.txt"));
        EdgeAmbiguity countEdges = new EdgeAmbiguity();
        assertThat(countEdges.edgeCompFind(testGraph)).isEqualTo(4);
    }

}
