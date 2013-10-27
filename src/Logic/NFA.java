package Logic;

import Enums.Operation;
import Models.Graph;
import Models.NFADecision;
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

    private NFADecision characterSwitch(String s)
    {
        NFADecision decision;
        switch(s.charAt(0))
        {
            case 'a' | 'A':
            case 'b' | 'B':
                String beginning;
                String leftover;
                Operation op;
                if(s.length() > 1)
                {
                    beginning = String.valueOf(s.charAt(0));
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

                    if(op != Operation.NONE)
                    {
                        if(s.length() > 2)
                        {
                            leftover = s.substring(2);
                        }
                        else
                        {
                            leftover = null;
                        }
                    }
                    else
                    {
                        leftover = null;
                    }
                }
                else
                {
                    beginning = String.valueOf(s.charAt(0));
                    op = Operation.NONE;
                    leftover = null;
                }
                decision = new NFADecision(beginning, leftover, op);
                break;
            case '(':
                decision = getWithinParenths(s);
                break;
            default:
                decision = null;
                break;
        }

        return decision;
    }

    private NFADecision getWithinParenths(String s)
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

                String beginning = String.copyValueOf(characters, 1, i - 1);
                String leftover;
                if(op == Operation.NONE)
                {
                    int opLength = i + 1;
                    if(characters.length > opLength)
                    {
                        leftover = String.copyValueOf(characters, opLength, characters.length - opLength);
                    }
                    else
                    {
                        leftover = null;
                    }
                }
                else
                {
                    int opLength = i + 2;
                    if(characters.length > opLength)
                    {
                        leftover = String.copyValueOf(characters, opLength, characters.length - opLength);
                    }
                    else
                    {
                        leftover = null;
                    }
                }

                return new NFADecision(beginning, leftover, op);
            }
        }

        return null;
    }
}