package Models;

import Enums.TransitionType;
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

    @Test
    public void simpleGraph()
    {
        Graph g = Graph.SimpleGraph(TransitionType.A);
        Assert.assertTrue(g.containsEdge(0, 1, TransitionType.A));
        Assert.assertTrue(g.getStateAt(0).getStartState());
        Assert.assertTrue(g.getStateAt(1).getFinalState());
    }

    @Test
    public void concatGraph()
    {
        Graph g = Graph.SimpleGraph(TransitionType.A);
        Graph h = Graph.SimpleGraph(TransitionType.B);
        Graph concat = Graph.ConcatGraphs(g, h);

        Graph test = new Graph();
        State first = test.addVertex();
        first.setStartState();
        test.addVertex();
        test.addVertex();
        test.addVertex();
        test.addEdge(0,1,TransitionType.A);
        test.addEdge(1,2,TransitionType.EPSILON);
        test.addEdge(2,3,TransitionType.B);
        test.getStateAt(3).setFinalState();
        Assert.assertTrue(concat.equals(test));
    }

    @Test
    public void kleeneGraph()
    {
        Graph g = Graph.SimpleGraph(TransitionType.A);
        g = Graph.KleeneGraph(g);

        //    - -  -  -
        //  /   / e \   \
        // 0 - 1  A  2 -  3
        Graph expected = new Graph();
        expected.addVertex().setStartState();
        expected.addVertex();
        expected.addVertex();
        expected.addVertex();
        expected.addEdge(0, 3, TransitionType.EPSILON);
        expected.addEdge(0, 1, TransitionType.EPSILON);
        expected.addEdge(2, 1, TransitionType.EPSILON);
        expected.addEdge(2, 3, TransitionType.EPSILON);
        expected.addEdge(1, 2, TransitionType.A);
        expected.getStateAt(3).setFinalState();

        Assert.assertTrue(g.toString() + "\n" + expected.toString() + "\n", g.equals(expected));

    }
}
