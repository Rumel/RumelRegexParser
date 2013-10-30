package Logic;

import Models.DFAState;

import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/29/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class RumelRegexParser {

    public static void main(String[] args)
    {
        DFA dfa = new DFA();
        Scanner minion = new Scanner(System.in);
        List<DFAState> list = dfa.getDFA(new NFA().makeNFA(minion.nextLine().trim()));
        String input;
        while((input = minion.nextLine()) != null)
        {
            if(dfa.accepted(list, input))
            {
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }
        }
    }
}
