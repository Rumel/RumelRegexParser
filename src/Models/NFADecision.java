package Models;

import Enums.Operation;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/26/13
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class NFADecision {
    private String beginning;
    private String leftovers;
    private Operation operation;

    public NFADecision(String beginningValue, String leftoversValue, Operation operationValue)
    {
        beginning = beginningValue;
        leftovers = leftoversValue;
        operation = operationValue;
    }

    public String getBeginning()
    {
        return beginning;
    }

    public String getLeftovers()
    {
        return leftovers;
    }

    public Operation getOperation()
    {
        return operation;
    }
}
