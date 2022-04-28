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

        mainLoop: do
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
            int leftPCount = 0;     //Beginning parenthesis count
            int rightPCount = 0;    //End parenthesis count

            float chToF = 0;
            float floatToPush = 0;

            float expValue = 0;
            boolean expNeg = false;

            stringCheckLoop: while (lenPos <= stringSize)
            {
                char ch;
                if (lenPos < stringSize)
                {
                    ch = inputExpr.charAt(lenPos);
                }
                else
                {
                    ch = 'q';
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
                                chToF = charToFloat(ch);
                                floatCreate.push(chToF);
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
                        continue stringCheckLoop;
                    
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
                            case 'q':
                                currentState = 12;
                                break;
                            case '_':
                                currentState = 14;
                                break;
                            case 'd':
                            case 'f':
                                currentState = 17;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue stringCheckLoop;
                    
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
                            case 'e':
                                currentState = 5;
                                break;
                            case 'd':
                            case 'f':
                                currentState = 17;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue stringCheckLoop;

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
                            case 'q':
                                currentState = 12;
                                break;
                            case '_':
                                currentState = 15;
                                break;
                            case 'd':
                            case 'f':
                                currentState = 17;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue stringCheckLoop;
                    
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
                        continue stringCheckLoop;
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
                            case 'q':
                                currentState = 12;
                                break;
                            case '_':
                                currentState = 16;
                                break;
                            case 'd':
                            case 'f':
                                currentState = 17;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue stringCheckLoop;
                    
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
                        continue stringCheckLoop;
                    
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
                        continue stringCheckLoop;
                    case 9:
                        switch (ch)
                        {
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
                            case 'q':
                                currentState = 12;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue stringCheckLoop;
                    
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
                        continue stringCheckLoop;
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
                        continue stringCheckLoop;
                    
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
                        continue stringCheckLoop;
                    
                    //States 14, 15, 16 are underlines between digits
                    case 14:
                        switch (ch)
                        {
                            case '_':
                                currentState = 14;
                                break;
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
                            default:
                                currentState = 0;
                                break;
                        }
                        continue stringCheckLoop;
                    case 15:
                        switch (ch)
                        {
                            case '_':
                                currentState = 15;
                                break;
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
                        continue stringCheckLoop;
                    case 16:
                        switch (ch)
                        {
                            case '_':
                                currentState = 16;
                                break;
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
                        continue stringCheckLoop;
                    
                    //State 17 is the float and double notation
                    case 17:
                        switch (ch)
                        {
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
                            case 'q':
                                currentState = 12;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue stringCheckLoop;

                    //Final Accept State
                    case 12:
                    break stringCheckLoop;
                    
                    //Trash State
                    case 0:
                        break stringCheckLoop;
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
    public static void floatFormation(Stack floatCreate, Stack postFix)
    {
        

        for (int i = 0; i < floatCreate.size(); i++)
        {

        }
    }
}
