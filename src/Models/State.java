package Models;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/23/13
 * Time: 11:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class State {
    private int stateNumber;
    private boolean startState = false;
    private boolean finalState = false;

    public State(int n)
    {
        stateNumber = n;
    }

    public void setStateNumber(int n)
    {
        stateNumber = n;
    }

    public int getStateNumber()
    {
        return stateNumber;
    }

    public void setStartState(){
        startState = true;
    }

    public void unsetStartState()
    {
        startState = false;
    }

    public boolean getStartState()
    {
        return startState;
    }

    public void setFinalState()
    {
        finalState = true;
    }

    public void unsetFinalState()
    {
        finalState = false;
    }

    public boolean getFinalState()
    {
        return finalState;
    }

    @Override
    public boolean equals(Object other)
    {
        return stateNumber == ((State)other).stateNumber;
    }

    @Override
    public String toString()
    {
        return String.format("State %d", stateNumber);
    }
}
