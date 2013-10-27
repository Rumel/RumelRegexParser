package Models;

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

    public void characterSwitch(String s)
    {
        switch(s.charAt(0))
        {
            case 'a' | 'A':
            case 'b' | 'B':
                if(s.length() > 1)
                {
                    switch(s.charAt(1))
                    {
                        case '*':
                            System.out.println(String.format("Found %c*", s.charAt(0)));
                            break;
                        case '|':
                            System.out.println(String.format("Found %c|", s.charAt(0)));
                            break;
                        default:
                            System.out.println(String.format("Found %c", s.charAt(0)));
                            break;
                    }
                }
                break;
            case '(':
                String withinParenths = getWithinParenths(s).getFirst();
                break;
            default:
                System.out.println("Entered default");
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
