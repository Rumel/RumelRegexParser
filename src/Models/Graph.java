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
        if(containsVertex(v))
        {
            return map.get(new State(v));
        }
        else
        {
            return new ArrayList();
        }
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Set<State> set = map.keySet();
        for(State s: set)
        {
            if(s.getStartState())
            {
                sb.append("START ");
            }

            if(s.getFinalState())
            {
                sb.append("FINAL ");
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
}
