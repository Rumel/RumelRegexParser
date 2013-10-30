package Logic;

import Models.DFAState;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
    NFA nfa;
    DFA dfa;

    @Before
    public void setup()
    {
        nfa = new NFA();
        dfa = new DFA();
    }

    @Test
    public void testRegex_1()
    {
        List<DFAState> list = getList("ab");
        Assert.assertTrue(dfa.accepted(list, "ab"));
        Assert.assertFalse(dfa.accepted(list, "abb"));
        Assert.assertFalse(dfa.accepted(list, "bab"));
    }

    @Test
    public void testRegex_2()
    {
        List<DFAState> list = getList("a*b");
        Assert.assertTrue(dfa.accepted(list, "ab"));
        Assert.assertTrue(dfa.accepted(list, "aab"));
        Assert.assertTrue(dfa.accepted(list, "aaab"));
        Assert.assertTrue(dfa.accepted(list, "aaaab"));
        Assert.assertTrue(dfa.accepted(list, "aaaaab"));
        Assert.assertTrue(dfa.accepted(list, "aaaaaab"));
        Assert.assertTrue(dfa.accepted(list, "aaaaaaab"));
        Assert.assertTrue(dfa.accepted(list, "aaaaaaaab"));
    }

    @Test
    public void testRegex_3()
    {
        List<DFAState> list = getList("(a|b)*abb");
        Assert.assertTrue(dfa.accepted(list, "abababababababbbabababababb"));
        Assert.assertTrue(dfa.accepted(list, "bbbbbbbbbabb"));
        Assert.assertTrue(dfa.accepted(list, "bbbaaaaabbbbbabb"));
        Assert.assertTrue(dfa.accepted(list, "bababababaabb"));
        Assert.assertTrue(dfa.accepted(list, "babababb"));
        Assert.assertTrue(dfa.accepted(list, "bbbbbbabb"));
        Assert.assertTrue(dfa.accepted(list, "aabb"));
        Assert.assertFalse(dfa.accepted(list, "b"));
        Assert.assertFalse(dfa.accepted(list, "babbaab"));
        Assert.assertFalse(dfa.accepted(list, "babba"));
        Assert.assertFalse(dfa.accepted(list, "babbaaaaaa"));
    }

    @Test
    public void testRegex_4()
    {
        List<DFAState> list = getList("(a|b)*a");
        Assert.assertTrue(dfa.accepted(list, "aaaa"));
        Assert.assertFalse(dfa.accepted(list, "bbb"));
        Assert.assertTrue(dfa.accepted(list, "aba"));
        Assert.assertTrue(dfa.accepted(list, "bba"));
        Assert.assertFalse(dfa.accepted(list, "e"));
        Assert.assertTrue(dfa.accepted(list, "a"));
    }

    @Test
    public void testRegex_5()
    {
        List<DFAState> list = getList("((a|b)(a|b))*");
        Assert.assertTrue(dfa.accepted(list, "abbabb"));
        Assert.assertTrue(dfa.accepted(list, "e"));
        Assert.assertTrue(dfa.accepted(list, "aa"));
        Assert.assertTrue(dfa.accepted(list, "ab"));
        Assert.assertFalse(dfa.accepted(list, "aaaaa"));
        Assert.assertFalse(dfa.accepted(list, "bba"));
    }

    @Test
    public void testRegex_6()
    {
        List<DFAState> list = getList("aaa*b*a*a");
        Assert.assertTrue(dfa.accepted(list, "aabaa"));
        Assert.assertTrue(dfa.accepted(list, "aaa"));
        Assert.assertTrue(dfa.accepted(list, "aabba"));
        Assert.assertFalse(dfa.accepted(list, "abbaa"));
        Assert.assertFalse(dfa.accepted(list, "abbbbbbbbbbbbbbbbbbba"));
        Assert.assertFalse(dfa.accepted(list, "bbaa"));
    }

    private List<DFAState> getList(String s)
    {
        return dfa.getDFA(nfa.makeNFA(s));
    }
}
