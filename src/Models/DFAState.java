package Models;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/29/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class DFAState {

    private Set<Integer> baseSet;
    private int aTransition;
    private int bTransition;
    private Set<Integer> aSet;
    private Set<Integer> bSet;
    private boolean marked;
    private boolean isFinalState;

    public DFAState()
    {
        baseSet = new TreeSet<>();
        aSet = new TreeSet<>();
        bSet = new TreeSet<>();
        aTransition = -1;
        bTransition = -1;
        marked = false;
        isFinalState = false;
    }

    public DFAState(Set<Integer> baseSetValue)
    {
        baseSet = baseSetValue;
        aSet = new TreeSet<>();
        bSet = new TreeSet<>();
        aTransition = -1;
        bTransition = -1;
        marked = false;
        isFinalState = false;
    }

    public DFAState(Set<Integer> baseSetValue, Set<Integer> aSetValue, Set<Integer> bSetValue)
    {
        baseSet = baseSetValue;
        aSet = aSetValue;
        bSet = bSetValue;
        aTransition = -1;
        bTransition = -1;
        marked = false;
        isFinalState = false;
    }

    public Set<Integer> getBaseSet()
    {
        return baseSet;
    }

    public void setBaseSet(Set<Integer> set)
    {
        baseSet = set;
    }

    public Set<Integer> getASet()
    {
        return aSet;
    }

    public void setASet(Set<Integer> set)
    {
        aSet = set;
    }

    public void addToASet(Set<Integer> set)
    {
        aSet.addAll(set);
    }

    public Set<Integer> getbSet()
    {
        return bSet;
    }

    public void setBSet(Set<Integer> set)
    {
        bSet = set;
    }

    public void addToBSet(Set<Integer> set)
    {
        bSet.addAll(bSet);
    }

    public void setATransition(int num)
    {
        aTransition = num;
    }

    public int getATransition()
    {
        return aTransition;
    }

    public void setBTransition(int num)
    {
        bTransition = num;
    }

    public int getBTransition()
    {
        return bTransition;
    }

    public boolean getMarked()
    {
        return marked;
    }

    public void setMarked()
    {
        marked = true;
    }

    public void unMark()
    {
        marked = false;
    }

    public boolean getIsFinalState()
    {
        return isFinalState;
    }

    public void setFinalState()
    {
        isFinalState = true;
    }

    public void unsetFinalState()
    {
        isFinalState = false;
    }

    @Override
    public String toString()
    {
        if(isFinalState)
        {
            return String.format("(FINAL)\ne: %s a: %s b: %s aT: %d bT: %d",
                    baseSet.toString(), aSet.toString(), bSet.toString(), aTransition, bTransition);
        }
        else{
            return String.format("e: %s a: %s b: %s aT: %d bT: %d",
                    baseSet.toString(), aSet.toString(), bSet.toString(), aTransition, bTransition);
        }

    }
}
