package Models;

import Enums.TransitionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Graph {
    private int stateNumber = 0;
    HashMap<State, List<Transition>> map;

    public Graph()
    {
        map = new HashMap();
    }

    public static Graph SimpleGraph(TransitionType transitionType)
    {
        Graph g = new Graph();
        State s = g.addVertex();
        s.setStartState();
        State f = g.addVertex();
        f.setFinalState();
        g.addEdge(s.getStateNumber(), f.getStateNumber(), transitionType);
        return g;
    }

    public static Graph ConcatGraphs(Graph first, Graph second)
    {
        Set<State> firstSet = first.getVertexes();
        Set<State> secondSet = second.getVertexes();
        int firstSize = firstSet.size();
        for(int i = 0; i < secondSet.size(); i++)
        {
            first.addVertex();
        }

        first.addEdge(firstSize - 1, firstSize, TransitionType.EPSILON);
        first.getStateAt(firstSize - 1).unsetFinalState();

        for(State s: secondSet)
        {
            State newState = first.getStateAt(firstSize + s.getStateNumber());
            int stateNumber = s.getStateNumber();
            List<Transition> edges = second.edgesOf(stateNumber);
            for(Transition t: edges)
            {
                first.addEdge(newState.getStateNumber(), firstSize + t.getStateNumber(), t.getTransitionType());
            }
        }
        first.getStateAt(first.size() - 1).setFinalState();
        return first;
    }

    public static Graph KleeneGraph(Graph graph)
    {
        Graph g = new Graph();
        State s = g.addVertex();
        s.setStartState();
        g = Graph.ConcatGraphs(g, graph);
        // remove final state on graph
        g.getStateAt(g.size() - 1).unsetFinalState();
        State f = g.addVertex();
        f.setFinalState();

        int size = g.size();


        // 0 needs to connect to end
        // 1 needs to connect to end -1 in reverse
        g.addEdge(0, size - 1, TransitionType.EPSILON);
        g.addEdge(size - 2, 1, TransitionType.EPSILON);
        g.addEdge(size - 2, size -1, TransitionType.EPSILON);

        return g;
    }

    public static Graph OrGraph(Graph first, Graph second)
    {

        return null;
    }

    public boolean addEdge(int sourceVertex, int targetVertex, TransitionType transitionType) {
        if(this.containsVertex(sourceVertex))
        {
            Transition transition = new Transition(targetVertex, transitionType);
            if(!map.get(getStateAt(sourceVertex)).contains(transition))
            {
                map.get(getStateAt(sourceVertex)).add(transition);
                return true;
            }
        }

        return false;
    }

    public State addVertex() {
        State s = new State(stateNumber);
        map.put(s, new ArrayList());
        stateNumber++;
        return s;
    }

    public boolean containsEdge(int sourceVertex, int targetVertex, TransitionType transitionType) {
        List<Transition> transitions = map.get(getStateAt(sourceVertex));
        if(transitions != null)
        {
            Transition t = new Transition(targetVertex, transitionType);
            return transitions.contains(t);
        }
        return false;
    }

    public boolean containsVertex(int v) {
        Set<State> set = map.keySet();
        for(State s: set)
        {
            if(s.getStateNumber() == v)
            {
                return true;
            }
        }
        return false;
    }

    public List<Transition> edgesOf(int v) {
        Set<State> set = map.keySet();
        for(State s: set)
        {
            if(s.getStateNumber() == v)
            {
                return map.get(s);
            }
        }
        return new ArrayList();
    }

    public boolean removeEdge(int sourceVertex, int targetVertex) {
        return false; //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeVertex(int v) {
        if(map.containsKey(v))
        {
            map.remove(v);
        }

        return true;
    }

    public State getStateAt(int stateNumber)
    {
        Set<State> set = map.keySet();
        for(State s: set)
        {
            if(s.getStateNumber() == stateNumber)
            {
                return s;
            }
        }
        return null;
    }

    public Set<State> getVertexes()
    {
        return map.keySet();
    }

    public int size()
    {
        return map.size();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.size(); i++)
        {
            State s = getStateAt(i);
            if(s.getStartState())
            {
                sb.append("(START)\n");
            }

            if(s.getFinalState())
            {
                sb.append("(FINAL)\n");
            }
            sb.append(String.format("%d: ", s.getStateNumber()));
            List<Transition> transitions = map.get(s);
            for(Transition t: transitions)
            {
                sb.append(String.format("%s,", t.toString()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other)
    {
        Graph o = (Graph)other;
        if(this.size() != o.size())
        {
            return false;
        }

        for(int i = 0; i < this.size(); i++)
        {
            State s = this.getStateAt(i);
            State otherS = o.getStateAt(i);
            if(s.getFinalState() != otherS.getFinalState())
            {
                return false;
            }
            if(s.getStartState() != otherS.getStartState())
            {
                return false;
            }
            List<Transition> theseEdges = this.edgesOf(i);
            List<Transition> otherEdges = o.edgesOf(i);
            for(Transition t: theseEdges)
            {
                if(!otherEdges.contains(t))
                {
                    return false;
                }
            }
            for(Transition t: otherEdges)
            {
                if(!theseEdges.contains(t))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
