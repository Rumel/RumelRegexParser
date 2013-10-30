package Logic;

import Enums.TransitionType;
import Models.DFAState;
import Models.Graph;
import Models.Transition;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/28/13
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class DFA {

    public DFA()
    {

    }

    public List<DFAState> getDFA(Graph nfa)
    {
        List<DFAState> list = new ArrayList<>();
        Set epsilonSet = getEpsilonTransitions(nfa, 0);
        DFAState dfaState = new DFAState(epsilonSet);
        list.add(dfaState);
        while(listHasUnamrked(list))
        {
            dfaState = getFirstUnmarked(list);
            Set aTransitions = getTransitionSet(nfa, dfaState.getBaseSet(), TransitionType.A);
            Set aEpsilons = getEpsilonTransitionsFromSet(nfa, aTransitions);
            DFAState aState =  listContainsDfaState(list, aEpsilons);
            if(aState != null)
            {
                int a = list.indexOf(aState);
                dfaState.setASet(aEpsilons);
                dfaState.setATransition(a);
            }
            else
            {
                if(aEpsilons.size() > 0)
                {
                    DFAState a = new DFAState(aEpsilons);
                    list.add(a);
                    int aNum = list.indexOf(a);
                    dfaState.setASet(aEpsilons);
                    dfaState.setATransition(aNum);
                }
            }

            Set bTransitions = getTransitionSet(nfa, dfaState.getBaseSet(), TransitionType.B);
            Set bEpsilons = getEpsilonTransitionsFromSet(nfa, bTransitions);
            DFAState bState = listContainsDfaState(list, bEpsilons);
            if(bState != null)
            {
                int b = list.indexOf(bState);
                dfaState.setBSet(bEpsilons);
                dfaState.setBTransition(b);
            }
            else
            {
                if(bEpsilons.size() > 0)
                {
                    DFAState b = new DFAState(bEpsilons);
                    list.add(b);
                    int bNum = list.indexOf(b);
                    dfaState.setBSet(bEpsilons);
                    dfaState.setBTransition(bNum);
                }
            }

            dfaState.setMarked();
        }

        list = setFinalState(list, nfa.size() - 1);

        return list;
    }

    public boolean accepted(List<DFAState> list, String regex)
    {
        int currentState = 0;
        char[] characters = regex.toCharArray();

        for(char c: characters)
        {
            switch(c){
                case 'a' | 'A':
                    DFAState aState = list.get(currentState);
                    int aTransition = aState.getATransition();
                    if(aTransition == -1)
                    {
                        return false;
                    }
                    currentState = aTransition;
                    break;
                case 'b' | 'B':
                    DFAState bState = list.get(currentState);
                    int bTransition = bState.getBTransition();
                    if(bTransition == -1)
                    {
                        return false;
                    }
                    currentState = bTransition;
                    break;
                default:
                    return false;
            }
        }

        DFAState finalState = list.get(currentState);
        if(finalState.getIsFinalState())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private DFAState listContainsDfaState(List<DFAState> list, Set<Integer> s)
    {
        for(DFAState dfs: list)
        {
            if(dfs.getBaseSet().equals(s))
            {
                return dfs;
            }
        }
        return null;
    }

    private DFAState getFirstUnmarked(List<DFAState> list)
    {
        for(DFAState d: list)
        {
            if(!d.getMarked())
            {
                return d;
            }
        }
        return null;
    }

    private boolean listHasUnamrked(List<DFAState> list)
    {
        for(DFAState dfas: list)
        {
            if(!dfas.getMarked())
            {
                return true;
            }
        }
        return false;
    }

    private List<DFAState> setFinalState(List<DFAState> list, int finalStateNum)
    {
        for(DFAState dfas: list)
        {
            if(dfas.getBaseSet().contains(finalStateNum))
            {
                dfas.setFinalState();
            }
        }
        return list;
    }

    private Set getEpsilonTransitions(Graph nfa, int start)
    {
        Set set = new TreeSet();
        set.add(start);
        List<Transition> edges = nfa.edgesOf(start);
        for(Transition t: edges)
        {
            if(t.getTransitionType() == TransitionType.EPSILON)
            {
                set.addAll(getEpsilonTransitions(nfa, t.getStateNumber()));
            }
        }
        return set;
    }

    private Set getEpsilonTransitionsFromSet(Graph nfa, Set set)
    {
        Set s = new TreeSet();
        for(int k: (Set<Integer>)set)
        {
            s.addAll(getEpsilonTransitions(nfa, k));
        }
        return s;
    }

    private Set getTransitionSet(Graph nfa, Set set, TransitionType transitionType)
    {
        Set retSet = new TreeSet();
        for(int i: (Set<Integer>)set)
        {
            List<Transition> edges = nfa.edgesOf(i);
            for(Transition t: edges)
            {
                if(t.getTransitionType() == transitionType)
                {
                    retSet.add(t.getStateNumber());
                }
            }
        }
        return retSet;
    }
}
