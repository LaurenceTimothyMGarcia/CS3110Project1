//Laurence Timothy M. Garcia
//CS 3110
//Professor Qichao Dong
//May 4th, 2022

import java.util.Scanner;
import java.util.Stack;

public class FloatPointExpressionPDA 
{
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);

        String inputExp = keyboardInput(kb);

        do
        {
            inputExp = inputExp.toLowerCase();
            int stringSize = inputExp.length();

            Stack floatStack = new Stack<>();
            Stack opStack = new Stack<>();

            int currentState = 1;



        } while (inputExp != "q");
    }

    /*** Request to Input String ***/
    public static String keyboardInput(Scanner kb)
    {
        String inputString = "";

        System.out.println("Input String here (Type in q to quit): ");
        inputString = kb.next();

        return inputString;
    }

    
}
