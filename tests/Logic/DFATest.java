package Logic;

import Models.Graph;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/28/13
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class DFATest {

    @Test
    public void testEpsilonTransitionOr()
            throws Exception
    {
        DFA dfa = new DFA();
        NFA nfa = new NFA();
        Graph g = nfa.makeNFA("a|b");
        List list = getEpsilonTransitions(dfa, g, 0);
        list.toString();
    }

    @Test
    public void testTest()
    {
        NFA nfa = new NFA();
        Graph g = nfa.makeNFA("(a|b)*abb");
        DFA.getDFA(g);
    }


    @Test
    public void testTest2()
    {
        NFA nfa = new NFA();
        Graph g = nfa.makeNFA("(a|b)*abb");
        System.out.println(g.toString());
        DFA.getDFA2(g);
    }

    private List getEpsilonTransitions(DFA dfa, Graph nfa, int start)
            throws Exception
    {
        Method method = dfa.getClass().getDeclaredMethod("getEpsilonTransitions", Graph.class, int.class);
        method.setAccessible(true);

        return (List)method.invoke(dfa, nfa, start);
    }
}
