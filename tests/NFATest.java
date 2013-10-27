import Logic.NFA;
import Enums.Operation;
import Models.NFADecision;
import org.junit.Assert;
import org.junit.Before;
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

    //    How to test private methods
    //
    //    MyClass myClass = new MyClass();
    //    Method method = MyClass.class.getDeclaredMethod("myMethod", String.class);
    //    method.setAccessible(true);
    //    String output = (String) method.invoke(myClass, "some input");

    @Before
    public void setup()
    {

    }

    @Test
    public void testParenthsOutside()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        Assert.assertEquals("Expected to find 'a'", "a", ((NFADecision)method.invoke(nfa,"(a)")).getBeginning());
        Assert.assertEquals("Expected to find 'ab'", "ab", ((NFADecision)method.invoke(nfa, "(ab)")).getBeginning());
        Assert.assertEquals("Expected to find 'abb'", "abb", ((NFADecision)method.invoke(nfa, "(abb)")).getBeginning());
        Assert.assertEquals("Expected to find 'a'", "a", ((NFADecision)method.invoke(nfa, "(a)a")).getBeginning());
        Assert.assertEquals("Expected to find 'ab'", "ab", ((NFADecision)method.invoke(nfa, "(ab)b")).getBeginning());
        Assert.assertEquals("Expected to find 'abb'", "abb", ((NFADecision)method.invoke(nfa,"(abb)ba")).getBeginning());
    }

    @Test
    public void testNestedParenths()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        Assert.assertEquals("Expected to find '(a)a'", "(a)a", ((NFADecision)method.invoke(nfa, "((a)a)")).getBeginning());
        Assert.assertEquals("Expected to find '((a)a(b))'", "((a)a(b))", ((NFADecision)method.invoke(nfa, "(((a)a(b)))")).getBeginning());
        Assert.assertEquals("Expected to find '((a)b)a'", "((a)b)a", ((NFADecision)method.invoke(nfa, "(((a)b)a)")).getBeginning());
    }

    @Test
    public void testParenthOperations()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        NFADecision nfa1 = (NFADecision)method.invoke(nfa, "(a)*");
        Assert.assertEquals("a", nfa1.getBeginning());
        Assert.assertEquals(Operation.KLEENE, nfa1.getOperation());

        NFADecision nfa2 = (NFADecision)method.invoke(nfa, "(a)|(ab)");
        Assert.assertEquals("a", nfa2.getBeginning());
        Assert.assertEquals(Operation.OR, nfa2.getOperation());

        NFADecision nfa3 = (NFADecision)method.invoke(nfa, "(a)");
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
        Assert.assertEquals("a", nfa1.getBeginning());
        Assert.assertEquals(null, nfa1.getLeftovers());
        Assert.assertEquals(Operation.KLEENE, nfa1.getOperation());

        NFADecision nfa2 = getNFADecision(nfa, method, "(a)|(ab)");
        Assert.assertEquals("a", nfa2.getBeginning());
        Assert.assertEquals("(ab)", nfa2.getLeftovers());
        Assert.assertEquals(Operation.OR, nfa2.getOperation());

        NFADecision nfa3 = getNFADecision(nfa, method, "(a)");
        Assert.assertEquals("a", nfa3.getBeginning());
        Assert.assertEquals(null, nfa3.getLeftovers());
        Assert.assertEquals(Operation.NONE, nfa3.getOperation());

        NFADecision nfa4 = getNFADecision(nfa, method, "(a)ab");
        Assert.assertEquals("a", nfa4.getBeginning());
        Assert.assertEquals("ab", nfa4.getLeftovers());
        Assert.assertEquals(Operation.NONE, nfa3.getOperation());
    }

    @Test
    public void testCharacterSwitch()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("characterSwitch", String.class);
        method.setAccessible(true);

        NFADecision nfa1 = getNFADecision(nfa, method, "a*b(ab)");
        Assert.assertEquals("a", nfa1.getBeginning());
        Assert.assertEquals("b(ab)", nfa1.getLeftovers());
        Assert.assertEquals(Operation.KLEENE, nfa1.getOperation());

        NFADecision nfa2 = getNFADecision(nfa, method, "a|b");
        Assert.assertEquals("a", nfa2.getBeginning());
        Assert.assertEquals("b", nfa2.getLeftovers());
        Assert.assertEquals(Operation.OR, nfa2.getOperation());

        NFADecision nfa3 = getNFADecision(nfa, method, "(a|b)|(a|b)");
        Assert.assertEquals("a|b", nfa3.getBeginning());
        Assert.assertEquals("(a|b)", nfa3.getLeftovers());
        Assert.assertEquals(Operation.OR, nfa3.getOperation());

        NFADecision nfa4 = getNFADecision(nfa, method, "(ab)*(bbbbb)*");
        Assert.assertEquals("ab", nfa4.getBeginning());
        Assert.assertEquals("(bbbbb)*", nfa4.getLeftovers());
        Assert.assertEquals(Operation.KLEENE, nfa4.getOperation());

        NFADecision nfa5 = getNFADecision(nfa, method, "(ab)(bbbbb)*");
        Assert.assertEquals("ab", nfa5.getBeginning());
        Assert.assertEquals("(bbbbb)*", nfa5.getLeftovers());
        Assert.assertEquals(Operation.NONE, nfa5.getOperation());
    }

    private NFADecision getNFADecision(NFA nfa, Method method, String regex)
            throws Exception
    {
        return (NFADecision)method.invoke(nfa, regex);
    }
}
