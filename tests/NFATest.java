import Logic.NFA;
import Enums.Operation;
import Models.Tuple;
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

        Assert.assertEquals("Expected to find 'a'", "a", ((Tuple<String, Operation>)method.invoke(nfa,"(a)")).getFirst());
        Assert.assertEquals("Expected to find 'ab'", "ab", ((Tuple<String, Operation>)method.invoke(nfa, "(ab)")).getFirst());
        Assert.assertEquals("Expected to find 'abb'", "abb", ((Tuple<String, Operation>)method.invoke(nfa, "(abb)")).getFirst());
        Assert.assertEquals("Expected to find 'a'", "a", ((Tuple<String, Operation>)method.invoke(nfa, "(a)a")).getFirst());
        Assert.assertEquals("Expected to find 'ab'", "ab", ((Tuple<String, Operation>)method.invoke(nfa, "(ab)b")).getFirst());
        Assert.assertEquals("Expected to find 'abb'", "abb", ((Tuple<String, Operation>)method.invoke(nfa,"(abb)ba")).getFirst());
    }

    @Test
    public void testNestedParenths()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        Assert.assertEquals("Expected to find '(a)a'", "(a)a", ((Tuple<String, Operation>)method.invoke(nfa, "((a)a)")).getFirst());
        Assert.assertEquals("Expected to find '((a)a(b))'", "((a)a(b))", ((Tuple<String, Operation>)method.invoke(nfa, "(((a)a(b)))")).getFirst());
        Assert.assertEquals("Expected to find '((a)b)a'", "((a)b)a", ((Tuple<String, Operation>)method.invoke(nfa, "(((a)b)a)")).getFirst());
    }

    @Test
    public void testParenthOperations()
           throws Exception
    {
        NFA nfa = new NFA();
        Method method = NFA.class.getDeclaredMethod("getWithinParenths", String.class);
        method.setAccessible(true);

        Tuple t1 = (Tuple<String, Operation>)method.invoke(nfa, "(a)*");
        Assert.assertEquals("a", t1.getFirst());
        Assert.assertEquals(Operation.KLEENE, t1.getSecond());
        Tuple t2 = (Tuple<String, Operation>)method.invoke(nfa, "(a)|(ab)");
        Assert.assertEquals("a", t2.getFirst());
        Assert.assertEquals(Operation.OR, t2.getSecond());
        Tuple t3 = (Tuple<String, Operation>)method.invoke(nfa, "(a)");
        Assert.assertEquals("a", t3.getFirst());
        Assert.assertEquals(Operation.NONE, t3.getSecond());
    }
}
