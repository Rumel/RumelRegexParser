package Logic;

import Models.DFAState;
import Models.Graph;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/28/13
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class DFATest {
    NFA nfa;
    DFA dfa;

    @Before
    public void setup()
    {
        nfa = new NFA();
        dfa = new DFA();
    }

    @Test
    public void testEpsilonTransitionOr()
            throws Exception
    {
        Graph g = nfa.makeNFA("a|b");
        List list = getEpsilonTransitions(dfa, g, 0);
        list.toString();
    }

    @Test
    public void testDFA()
    {
        Graph g = nfa.makeNFA("(a|b)*abb");
        List<DFAState> list = dfa.getDFA(g);
        printDFA(list);
    }

    @Test
    public void testSimpleDFA()
    {
        Graph g = nfa.makeNFA("aaaa");
        List<DFAState> list = dfa.getDFA(g);
        printDFA(list);
    }

    @Test
    public void testSimpleDFA_1()
    {
        Graph g = nfa.makeNFA("ab");
        List<DFAState> list = dfa.getDFA(g);
        Assert.assertTrue(dfa.accepted(list, "ab"));
        Assert.assertFalse(dfa.accepted(list, "abb"));
        Assert.assertFalse(dfa.accepted(list, "bab"));
    }

    private List getEpsilonTransitions(DFA dfa, Graph nfa, int start)
            throws Exception
    {
        Method method = dfa.getClass().getDeclaredMethod("getEpsilonTransitions", Graph.class, int.class);
        method.setAccessible(true);

        return (List)method.invoke(dfa, nfa, start);
    }

    private void printDFA(List<DFAState> list)
    {
        for(int i =0 ; i < list.size(); i++)
        {
            System.out.println(String.format("%d {%s}", i , list.get(i).toString()));
        }
    }
}
