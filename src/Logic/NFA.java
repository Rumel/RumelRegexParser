package Logic;

import Enums.Operation;
import Models.Graph;
import Models.Tuple;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/23/13
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class NFA {

    public NFA()
    {

    }

    public Graph makeNFA(String regex)
    {
        return null;
    }

    private void characterSwitch(String s)
    {
        switch(s.charAt(0))
        {
            case 'a' | 'A':
            case 'b' | 'B':
                Operation op;
                if(s.length() > 1)
                {
                    switch(s.charAt(1))
                    {
                        case '*':
                            op = Operation.KLEENE;
                            break;
                        case '|':
                            op = Operation.OR;
                            break;
                        default:
                            op = Operation.NONE;
                            break;
                    }
                }
                else
                {
                    op = Operation.NONE;
                }
                break;
            case '(':
                Tuple parentheses = getWithinParenths(s);
                break;
            default:
                break;
        }
    }

    private Tuple<String, Operation> getWithinParenths(String s)
    {
        char[] characters = s.toCharArray();
        int foundParenths = 0;
        for(int i = 0; i < characters.length; i++)
        {
            if(characters[i] == '(')
            {
                foundParenths++;
            }
            else if(characters[i] == ')')
            {
                foundParenths--;
            }

            if(foundParenths == 0)
            {
                Operation op;
                //Parentheses closed!
                if(characters.length > i + 1)
                {
                    switch(characters[i+1])
                    {
                        case '*':
                            op = Operation.KLEENE;
                            break;
                        case '|':
                            op = Operation.OR;
                            break;
                        default:
                            op = Operation.NONE;
                            break;
                    }
                }
                else
                {
                    op = Operation.NONE;
                }

                return new Tuple(String.copyValueOf(characters, 1, i - 1), op);
            }
        }

        return null;
    }
}
