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

        String inputExpr = keyboardInput(kb);

        do
        {
            inputExpr = inputExpr.toLowerCase();
            int stringSize = inputExpr.length();

            //Stack to convert the string to float and stack for float fix
            Stack floatCreate = new Stack<>();
            Stack postFix = new Stack<>();

            //Trackers
            int currentState = 1;   //Keeps track of current state
            int lenPos = 0;         //Position in string
            int decPos = 0;         //Decimal position in string
            int expPos = 0;         //Exponent e position in string

            float expValue = 0;
            boolean expNeg = false;

            while (lenPos <= stringSize)
            {
                char ch = inputExpr.charAt(lenPos);
                lenPos++;

                switch (currentState)
                {
                    //State 1 - Starting state
                    case 1:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 2;
                                break;
                            case '.':
                                currentState = 3;
                                decPos = lenPos;
                                break;
                            case '(':
                                currentState = 8;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    case 2:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 2;
                                break;
                            case '.':
                                currentState = 3;
                                decPos = lenPos;
                                break;
                            case 'e':
                                currentState = 5;
                                expPos = lenPos;
                                break;
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                                currentState = 7;
                                break;
                            case ')':
                                currentState = 9;
                                break;
                            case ' ':
                                currentState = 10;
                                break;
                            default:
                                if (lenPos > stringSize)
                                {
                                    currentState = 12;
                                    break;
                                }
                                else
                                {
                                    currentState = 0;
                                    break;
                                }
                        }
                        break;
                    case 3:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 4;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    case 4:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 4;
                                break;
                            case 'e':
                                currentState = 5;
                                expPos = lenPos;
                                break;
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                                currentState = 7;
                                break;
                            case ')':
                                currentState = 9;
                                break;
                            case ' ':
                                currentState = 10;
                                break;
                            default:
                                if (lenPos > stringSize)
                                {
                                    currentState = 12;
                                    break;
                                }
                                else
                                {
                                    currentState = 0;
                                    break;
                                }
                        }
                        break;
                    case 5:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 6;
                                break;
                            case '+':
                            case '-':
                                currentState = 13;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    case 6:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 6;
                                break;
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                                currentState = 7;
                                break;
                            case '(':
                                currentState = 9;
                                break;
                            case ' ':
                                currentState = 10;
                            default:
                                if (lenPos > stringSize)
                                {
                                    currentState = 12;
                                    break;
                                }
                                else
                                {
                                    currentState = 0;
                                    break;
                                }
                        }
                        break;
                    case 7:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 2;
                                break;
                            case '.':
                                currentState = 3;
                                break;
                            case '(':
                                currentState = 8;
                                break;
                            case ' ':
                                currentState = 11;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    case 8:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 2;
                                break;
                            case '.':
                                currentState = 3;
                                break;
                            case '(':
                                currentState = 8;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    case 9:
                        switch (ch)
                        {
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                                currentState = 7;
                                break;
                            case ' ':
                                currentState = 10;
                                break;
                            default:
                                if (lenPos > stringSize)
                                {
                                    currentState = 12;
                                    break;
                                }
                                else
                                {
                                    currentState = 0;
                                    break;
                                }
                        }
                        break;
                    case 10:
                        switch (ch)
                        {
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                                currentState = 7;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    case 11:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 4;
                                break;
                            case '(':
                                currentState = 8;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    case 12:
                        break;
                    case 13:
                        switch (ch)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                currentState = 6;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                }
            }

            inputExpr = keyboardInput(kb);
        } while (inputExpr != "q");
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
