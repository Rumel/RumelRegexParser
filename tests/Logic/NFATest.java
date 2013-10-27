package Logic;

import Enums.Operation;
import Models.NFADecision;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/26/13
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class NFATest {

    @Test
    public void testParenthsOutside()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        Assert.assertEquals("Expected to find 'a'", "a", getNFADecision(nfa, method, "(a)").getBeginning());
        Assert.assertEquals("Expected to find 'ab'", "ab", getNFADecision(nfa, method, "(ab)").getBeginning());
        Assert.assertEquals("Expected to find 'abb'", "abb", getNFADecision(nfa, method, "(abb)").getBeginning());
        Assert.assertEquals("Expected to find 'a'", "a", getNFADecision(nfa, method, "(a)a").getBeginning());
        Assert.assertEquals("Expected to find 'ab'", "ab", getNFADecision(nfa, method,  "(ab)b").getBeginning());
        Assert.assertEquals("Expected to find 'abb'", "abb", getNFADecision(nfa, method, "(abb)ba").getBeginning());
    }

    @Test
    public void testNestedParenths()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        Assert.assertEquals("Expected to find '(a)a'", "(a)a", getNFADecision(nfa, method, "((a)a)").getBeginning());
        Assert.assertEquals("Expected to find '((a)a(b))'", "((a)a(b))", getNFADecision(nfa, method, "(((a)a(b)))").getBeginning());
        Assert.assertEquals("Expected to find '((a)b)a'", "((a)b)a", getNFADecision(nfa, method, "(((a)b)a)").getBeginning());
    }

    @Test
    public void testParenthOperations()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        NFADecision nfa1 = getNFADecision(nfa, method, "(a)*");
        Assert.assertEquals("a", nfa1.getBeginning());
        Assert.assertEquals(Operation.KLEENE, nfa1.getOperation());

        NFADecision nfa2 = getNFADecision(nfa, method, "(a)|(ab)");
        Assert.assertEquals("a", nfa2.getBeginning());
        Assert.assertEquals(Operation.OR, nfa2.getOperation());

        NFADecision nfa3 = getNFADecision(nfa, method, "(a)");
        Assert.assertEquals("a", nfa3.getBeginning());
        Assert.assertEquals(Operation.NONE, nfa3.getOperation());
    }

    @Test
    public void testParenthsLeftover()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        NFADecision nfa1 = getNFADecision(nfa, method, "(a)*");
        assertNFADecision(nfa1, "a", null, Operation.KLEENE, true);

        NFADecision nfa2 = getNFADecision(nfa, method, "(a)|(ab)");
        assertNFADecision(nfa2, "a", "(ab)", Operation.OR, true);

        NFADecision nfa3 = getNFADecision(nfa, method, "(a)");
        assertNFADecision(nfa3, "a", null, Operation.NONE, true);

        NFADecision nfa4 = getNFADecision(nfa, method, "(a)ab");
        assertNFADecision(nfa4, "a", "ab", Operation.NONE, true);
    }

    @Test
    public void testCharacterSwitch()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("characterSwitch", String.class);
        method.setAccessible(true);

        NFADecision nfa1 = getNFADecision(nfa, method, "a*b(ab)");
        assertNFADecision(nfa1, "a", "b(ab)", Operation.KLEENE, true);

        NFADecision nfa2 = getNFADecision(nfa, method, "a|b");
        assertNFADecision(nfa2, "a", "b", Operation.OR, true);

        NFADecision nfa3 = getNFADecision(nfa, method, "(a|b)|(a|b)");
        assertNFADecision(nfa3, "a|b", "(a|b)", Operation.OR, false);

        NFADecision nfa4 = getNFADecision(nfa, method, "(ab)*(bbbbb)*");
        assertNFADecision(nfa4, "ab", "(bbbbb)*", Operation.KLEENE, false);

        NFADecision nfa5 = getNFADecision(nfa, method, "(ab)(bbbbb)*");
        assertNFADecision(nfa5, "ab", "(bbbbb)*", Operation.NONE, false);
    }

    private NFADecision getNFADecision(NFA nfa, Method method, String regex)
            throws Exception
    {
        return (NFADecision)method.invoke(nfa, regex);
    }

    private void assertNFADecision(NFADecision decision, String beginning, String leftovers, Operation op, boolean isGraphReady)
    {
        Assert.assertEquals(beginning, decision.getBeginning());
        Assert.assertEquals(leftovers, decision.getLeftovers());
        Assert.assertEquals(op, decision.getOperation());
        Assert.assertEquals(isGraphReady, decision.getReadyToGraph());
    }
}
