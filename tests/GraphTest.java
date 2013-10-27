import Enums.TransitionType;
import Models.Graph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/25/13
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(JUnit4.class)
public class GraphTest {
    Graph graph;

    @Before
    public void setup()
    {
        graph = new Graph();
    }

    @Test
    public void addToGraph()
    {
        graph = new Graph();
        graph.addVertex();
        graph.containsVertex(0);
        Assert.assertEquals("The graph does not contain 0", true, true);
    }

    @Test
    public void addEdgeToGraph()
    {
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0,1, TransitionType.A);
        Assert.assertTrue(graph.containsVertex(0));
        Assert.assertTrue(graph.containsVertex(1));
        Assert.assertTrue(graph.containsEdge(0,1,TransitionType.A));
    }

    @Test
    public void addTwoEdgesToGraph()
    {
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0,1,TransitionType.A);
        graph.addEdge(0,1,TransitionType.B);
        Assert.assertTrue(graph.containsEdge(0,1,TransitionType.A));
        Assert.assertTrue(graph.containsEdge(0,1,TransitionType.B));
    }
}
