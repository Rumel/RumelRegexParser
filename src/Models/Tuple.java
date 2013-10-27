package Models;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/26/13
 * Time: 1:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tuple<F, S> {
    private F first;
    private S second;

    public Tuple(F firstValue, S secondValue)
    {
        first = firstValue;
        second = secondValue;
    }

    public F getFirst()
    {
        return first;
    }

    public void setFirst(F value)
    {
        first = value;
    }

    public S getSecond()
    {
        return second;
    }

    public void setSecond(S value)
    {
        second = value;
    }

    @Override
    public boolean equals(Object other)
    {
        Tuple o = (Tuple<F, S>)other;
        return this.getFirst().equals(o.getFirst()) &&
                this.getSecond().equals(o.getSecond());
    }

    @Override
    public String toString()
    {
        return String.format("(%s,%s)", this.getFirst().toString(), this.getSecond().toString());
    }
}
