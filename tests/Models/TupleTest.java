package Models;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/26/13
 * Time: 1:51 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class TupleTest {

    @Test
    public void testTuple()
    {
        String s1 = "s1";
        String s2 = "s2";
        Tuple t = new Tuple(s1, s2);
        Assert.assertEquals(t.getFirst(), s1);
        Assert.assertEquals(t.getSecond(), s2);
    }

    @Test
    public void changeTuple()
    {

        String s1 = "s1";
        String s2 = "s2";
        Tuple t = new Tuple(s1, s2);
        Assert.assertEquals(t.getFirst(), s1);
        Assert.assertEquals(t.getSecond(), s2);
        String newS1 = "newS1";
        String newS2 = "newS2";
        t.setFirst(newS1);
        t.setSecond(newS2);
        Assert.assertEquals(t.getFirst(), newS1);
        Assert.assertEquals(t.getSecond(), newS2);
    }

    @Test
    public void compareTuples()
    {
        String s1 = "s1";
        String s2 = "s2";
        Tuple t1 = new Tuple(s1, s2);
        Tuple t2 = new Tuple(s1, s2);
        Assert.assertEquals(t1.getFirst(), s1);
        Assert.assertEquals(t2.getSecond(), s2);
        Assert.assertEquals(t1, t2);
    }
}
