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
            Stack<Float> floatCreate = new Stack<>();
            Stack postFix = new Stack<>();

            //Trackers
            int currentState = 1;   //Keeps track of current state
            int lenPos = 0;         //Position in string
            int decPos = 0;         //Decimal position in string
            int expPos = 0;         //Exponent e position in string

            float cToF = 0;

            float expValue = 0;
            boolean expNeg = false;

            while (lenPos <= stringSize)
            {
                char ch;
                if (lenPos < stringSize)
                {
                    ch = inputExpr.charAt(lenPos);
                }
                else
                {
                    ch = '?';
                }

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
                                cToF = charToFloat(ch);
                                floatCreate.push(cToF);
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
                    
                    //State 2 are the digits before .
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
                            case '?':
                                currentState = 12;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    
                    //State 3 is .
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

                    //State 4 are the digits after .
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
                            case '?':
                                currentState = 12;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    
                    //State 5 is exponent e
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
                    //Case 6 are the digits after exponent e
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
                            case '?':
                                currentState = 12;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    
                    //State 7 is the operator state including +, -, *, /
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
                    
                    //States 8 and 9 are ( and ) respectively
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
                            case '?':
                                currentState = 12;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        break;
                    
                    //States 10 and 11 are white space
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
                    
                    //Final State
                    case 12:
                        break;
                    
                    //Determines if exponent e is positive or negative
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
                    
                    //States 14, 15, 16 are underlines between digits
                    case 14:
                        break;
                    case 15:
                        break;
                    case 16:
                        break;
                }
                System.out.println(ch);
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

    /*** Trades character for appropriate number ***/
    public static float charToFloat(char ch)
    {
        float valueReturn = 0;
        switch (ch)
        {
            case '0':
                valueReturn = 0;
                break;
            case '1':
                valueReturn = 1;
                break;
            case '2':
                valueReturn = 2;
                break;
            case '3':
                valueReturn = 3;
                break;
            case '4':
                valueReturn = 4;
                break;
            case '5':
                valueReturn = 5;
                break;
            case '6':
                valueReturn = 6;
                break;
            case '7':
                valueReturn = 7;
                break;
            case '8':
                valueReturn = 8;
                break;
            case '9':
                valueReturn = 9;
                break;
        }

        return valueReturn;
    }

    /*** Create float and push to postfix stack ***/

}
