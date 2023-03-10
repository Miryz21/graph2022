package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class BridgesTest {
    @Test
    void bridges1() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/18_graph1.txt"));
        CountBridges countBridges = new CountBridges();
        assertThat(countBridges.bridgeCounter(testGraph)).isEqualTo(1);
    }
    @Test
    void bridges2() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/18_graph2.txt"));
        CountBridges countBridges = new CountBridges();
        assertThat(countBridges.bridgeCounter(testGraph)).isEqualTo(8);
    }
    @Test
    void bridges3() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/18_graph3.txt"));
        CountBridges countBridges = new CountBridges();
        assertThat(countBridges.bridgeCounter(testGraph)).isEqualTo(3);
    }

}
