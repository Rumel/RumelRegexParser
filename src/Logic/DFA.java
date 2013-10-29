package Logic;

import Enums.TransitionType;
import Models.Graph;
import Models.Transition;

import java.util.ArrayList;
import java.util.List;

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

    public static Graph getDFA2(Graph nfa)
    {
        List epsilonList = getEpsilonTransitions(nfa, 0);
        System.out.println(epsilonList.toString());

        return null;
    }

    public static Graph getDFA(Graph nfa)
    {

        for(int i = 0; i < nfa.size(); i++)
        {
            List epsilonList = getEpsilonTransitions(nfa, i);
            List aList = new ArrayList();
            List bList = new ArrayList();

            // A
            for(int k: (List<Integer>)epsilonList)
            {
                List<Transition> edges = nfa.edgesOf(k);
                for(Transition t: edges)
                {
                    if(t.getTransitionType() == TransitionType.A)
                    {
                        aList.add(t.getStateNumber());
                    }
                }
            }

            // B
            for(int k: (List<Integer>)epsilonList)
            {
               List<Transition> edges = nfa.edgesOf(k);
               for(Transition t: edges)
               {
                   if(t.getTransitionType() == TransitionType.B)
                   {
                       bList.add(t.getStateNumber());
                   }
               }
            }
            System.out.println(String.format("{%d: e: %s A: %s B: %s}", i, epsilonList.toString(), aList.toString(), bList.toString()));
        }

        return null;
    }

    private static List getEpsilonTransitions(Graph nfa, int start)
    {
        List list = new ArrayList();
        list.add(start);
        List<Transition> edges = nfa.edgesOf(start);
        for(Transition t: edges)
        {
            if(t.getTransitionType() == TransitionType.EPSILON)
            {
                list.addAll(getEpsilonTransitions(nfa, t.getStateNumber()));
            }
        }
        return list;
    }
}
