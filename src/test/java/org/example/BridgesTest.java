package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class BridgesTest {
    @Test
    void count_bridges() throws FileNotFoundException{
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/bridges1.txt"));
        CountBridges countBridges = new CountBridges();
        assertThat(countBridges.bridgeCounter(testGraph)).isEqualTo(1);
    }
}
