package Models;

import Enums.TransitionType;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/24/13
 * Time: 12:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Transition {
    private int stateNumber;
    private TransitionType transitionType;

    public Transition(int num, TransitionType trannyType)
    {
        stateNumber = num;
        transitionType= trannyType;
    }

    public void setStateNumber(int num)
    {
        stateNumber = num;
    }

    public int getStateNumber()
    {
        return stateNumber;
    }

    public void setTransitionType(TransitionType trannyType)
    {
        transitionType = trannyType;
    }

    public TransitionType getTransitionType()
    {
        return transitionType;
    }

    @Override
    public boolean equals(Object o)
    {
        Transition t = (Transition)o;
        return t.getStateNumber() == stateNumber && t.getTransitionType() == transitionType;
    }

    @Override
    public String toString()
    {
        return String.format("%s -> %d", transitionType.toString(), stateNumber);
    }
}
