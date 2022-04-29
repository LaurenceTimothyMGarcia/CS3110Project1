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
            Stack<Float> floatStack = new Stack<>();
            Stack<Character> operatorStack = new Stack<>();

            //Trackers
            int currentState = 1;   //Keeps track of current state
            int lenPos = 0;         //Position in string
            int decPos = 0;         //Decimal position in string
            int expPos = 0;         //Exponent e position in string

            float floatToPush = 0;

            float expValue = 0;
            boolean expNeg = false;

            boolean pSingleEnd = false;

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

                System.out.println(inputExpr);

                lenPos++;

                System.out.println();
                System.out.println("Current State: " + currentState);
                System.out.println("Current Char: " + ch);
                System.out.println("Current Float: " + floatToPush);

                System.out.println(floatStack);
                System.out.println(operatorStack);

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
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
                                currentState = 2;
                                break;
                            case '.':
                                currentState = 3;
                                decPos = lenPos;
                                break;
                            case '(':
                                currentState = 8;
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                break;
                            case 'q':
                                break mainLoop;
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
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
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
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case ')':
                                currentState = 9;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case ' ':
                                currentState = 10;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                floatToPush = 0;
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
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
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
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
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
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case ')':
                                currentState = 9;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case ' ':
                                currentState = 10;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                floatToPush = 0;
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
                                expValue = floatCreation(lenPos, currentState, decPos, expPos, expValue, ch, floatStack);
                                break;
                            case '-':
                                expNeg = true;
                            case '+':
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
                                expValue = floatCreation(lenPos, currentState, decPos, expPos, expValue, ch, floatStack);
                                break;
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                                currentState = 7;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case '(':
                                currentState = 9;
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                break;
                            case ' ':
                                currentState = 10;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                floatToPush = 0;
                                break;
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
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
                                break;
                            case '.':
                                currentState = 3;
                                break;
                            case '(':
                                currentState = 8;
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                break;
                            case ' ':
                                currentState = 11;
                                expValue = 0;
                                floatToPush = 0;
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
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
                                break;
                            case '.':
                                currentState = 3;
                                break;
                            case '(':
                                currentState = 8;
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
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
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case ')':
                                currentState = 9;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case ' ':
                                currentState = 10;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                floatToPush = 0;
                                break;
                            case 'q':
                                currentState = 12;
                                System.out.println("q" + operatorStack);
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
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
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
                                currentState = 2;
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
                                break;
                            case '.':
                                currentState = 3;
                                decPos = lenPos;
                                break;
                            case '(':
                                currentState = 8;
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
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
                                expValue = floatCreation(lenPos, currentState, decPos, expPos, expValue, ch, floatStack);
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
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
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
                                floatToPush = floatCreation(lenPos, currentState, decPos, expPos, floatToPush, ch, floatStack);
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
                                expValue = floatCreation(lenPos, currentState, decPos, expPos, expValue, ch, floatStack);
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
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case ')':
                                currentState = 9;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                pemdasStack(floatStack, operatorStack, ch, pSingleEnd, lenPos, stringSize);
                                floatToPush = 0;
                                break;
                            case ' ':
                                currentState = 10;
                                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                                expValue = 0;
                                floatStack.push(floatToPush);
                                floatToPush = 0;
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
            }

            System.out.println(floatStack);
            System.out.println(operatorStack);

            //Adds the rest of the operations
            if (currentState == 12)
            {
                floatToPush = floatExponentAdd(floatToPush, expNeg, expValue);
                expValue = 0;

                floatStack.push(floatToPush);

                char paraEnd;
                System.out.println("OPERATOR STACK " + operatorStack);
                char paraBegCheck;

                //Checks if the last number is (#)
                if (operatorStack.size() > 1)
                {
                    paraEnd = operatorStack.peek();
                    if (paraEnd == ')')
                    {
                        paraEnd = operatorStack.pop();
                        paraBegCheck = operatorStack.peek();
                        if (paraEnd == ')' && paraBegCheck == '(')
                        {
                            floatStack.pop();
                            operatorStack.pop();
                        }
                    }
                }

                System.out.println("OPERATOR STACK " + operatorStack);

                //Keeps adding the rest of the floats
                while (operatorStack.size() >= 1)
                {
                    if (floatStack.size() <= 1)
                    {
                        break;
                    }

                    System.out.println(floatStack);

                    float temp2 = floatStack.pop();
                    float temp1 = floatStack.pop();

                    System.out.println("Temp 2: " + temp2);
                    System.out.println("Temp 1: " + temp1);

                    char opPop = operatorStack.pop();
                    System.out.println(floatStack);
                    System.out.println(opPop);
                    switch (opPop)
                    {
                        case '+':
                            temp1 = temp1 + temp2;
                            floatStack.push(temp1);
                            break;
                        case '-':
                            temp1 = temp1 - temp2;
                            floatStack.push(temp1);
                            break;
                        case '*':
                            System.out.println("HERE");
                            temp1 = temp1 * temp2;
                            floatStack.push(temp1);
                            break;
                        case '/':
                            temp1 = temp1 / temp2;
                            floatStack.push(temp1);
                            break;
                    }
                }

                System.out.println("Finished Calculation");
                System.out.println(floatStack.pop());
            }
            else
            {
                System.out.println("Input not valid");
            }
            
            System.out.println();

            inputExpr = keyboardInput(kb);
        } while (inputExpr != "q");
    }

    /*** Request to Input String ***/
    public static String keyboardInput(Scanner kb)
    {
        String inputString = "";

        System.out.println("Input String here (Type in q to quit): ");
        inputString = kb.nextLine();

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

    /*** Deals with pemdas order of operation ***/
    public static void pemdasStack(Stack<Float> floatStack, Stack<Character> operatorStack, char ch, boolean pSingleEnd, int lenPos, int stringSize)
    {
        char opPeek;
        float temp1;
        float temp2;
        float returnValue;

        if (operatorStack.size() <= 0)
        {
            operatorStack.push(ch);
            return;
        }

        opPeek = operatorStack.peek();

        //Checks if last value is (#)
        if (ch == ')' && opPeek == '(' && (lenPos == stringSize))
        {
            operatorStack.push(ch);
            pSingleEnd = true;
            return;
        }

        //Deals with paraenthesis part of pemdas
        if (ch == ')')
        {
            while (opPeek != '(')
            {
                ch = operatorStack.pop();
                opPeek = operatorStack.peek();

                System.out.println("Float Stack " + floatStack);
                System.out.println("Operator Stack " + operatorStack);

                System.out.println("ch " + ch);
                System.out.println("opPeek " + opPeek);

                if (floatStack.size() <= 1)
                {
                    break;
                }

                switch (opPeek)
                {
                    case '+':
                        temp2 = floatStack.pop();
                        temp1 = floatStack.pop();
                        switch (ch)
                        {
                            case '+':
                            case '-':
                                returnValue = temp1 + temp2;

                                floatStack.push(returnValue);
                                break;
                            case '*':
                            case '/':
                                returnValue = temp1 * temp2;
                                floatStack.push(returnValue);
                                break;
                        }
                        
                        break;
                    case '-':
                        switch (ch)
                        {
                            case '+':
                            case '-':
                                temp2 = floatStack.pop();
                                temp1 = floatStack.pop();
                                returnValue = temp1 - temp2;

                                floatStack.push(returnValue);
                                break;
                            case '*':
                            case '/':
                                operatorStack.push(ch);
                                break;
                        }
                        break;
                    case '*':
                        switch (ch)
                        {
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                                temp2 = floatStack.pop();
                                temp1 = floatStack.pop();
                                returnValue = temp1 * temp2;

                                floatStack.push(returnValue);
                                //operatorStack.pop();
                                break;
                        }
                        break;
                    case '/':
                        switch (ch)
                        {
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                                temp2 = floatStack.pop();
                                temp1 = floatStack.pop();
                                returnValue = temp1 / temp2;

                                floatStack.push(returnValue);
                                //operatorStack.pop();
                                break;
                        }
                        break;
                    case '(':
                        temp2 = floatStack.pop();
                        temp1 = floatStack.pop();
                        switch (ch)
                        {
                            case '+':
                                returnValue = temp1 + temp2;
                                floatStack.push(returnValue);
                                break;
                            case '-':
                                returnValue = temp1 - temp2;
                                floatStack.push(returnValue);
                                break;
                            case '*':
                                returnValue = temp1 * temp2;
                                floatStack.push(returnValue);
                                break;
                            case '/':
                                returnValue = temp1 / temp2;
                                floatStack.push(returnValue);
                                break;
                        }

                        break;
                }
            }

            operatorStack.pop();
        }

        //Primary check and operation
        switch (opPeek)
        {
            case '+':
                operatorStack.push(ch);
                break;
            case '-':
                switch (ch)
                {
                    case '+':
                    case '-':
                        temp2 = floatStack.pop();
                        temp1 = floatStack.pop();
                        returnValue = temp1 - temp2;

                        floatStack.push(returnValue);
                        operatorStack.push(ch);
                        break;
                    case '*':
                    case '/':
                        operatorStack.push(ch);
                        break;
                    case '(':
                        operatorStack.push(ch);
                        break;
                }
                break;
            case '*':
                switch (ch)
                {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        temp2 = floatStack.pop();
                        temp1 = floatStack.pop();
                        returnValue = temp1 * temp2;

                        floatStack.push(returnValue);
                        operatorStack.pop();
                        operatorStack.push(ch);
                        break;
                    case '(':
                        operatorStack.push(ch);
                        break;
                }
                break;
            case '/':
                switch (ch)
                {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        temp2 = floatStack.pop();
                        temp1 = floatStack.pop();
                        returnValue = temp1 / temp2;

                        floatStack.push(returnValue);
                        operatorStack.pop();
                        operatorStack.push(ch);
                        break;
                    case '(':
                        operatorStack.push(ch);
                        break;
                }
                break;
            case '(':
                if (ch == ')')
                {
                    break;
                }
                operatorStack.push(ch);
                break;
        }
    }

    /*** Adds any Exponent e values to the float ***/
    public static float floatExponentAdd(float floatToPush, boolean expNeg, float expValue)
    {
        if (expNeg) //Checks if e was negative
        {
            expValue *= -1;
        }

        if (expValue != 0)  //adds the exp to the final float
        {
            float exp10 = 1;

            if (expValue > 0)
            {
                for (int i = 0; i < expValue; i++)
                {
                    exp10 *= 10;
                }
            }
            else
            {
                for (int i = 0; i > expValue; i--)
                {
                    exp10 /= 10;
                }
            }

            floatToPush *= exp10;
        }

        return floatToPush;
    }

    /*** Adds the char into the final total ***/
    public static float floatCreation(int lenPos, int state, int decPos, int expPos, float fixedString, char ch, Stack floatStack)
    {
        switch (state)
        {
            case 1: //First digit
                fixedString = charToFloat(ch);
                break;
            case 2: //All digits before the decimal
                fixedString *= 10;
                fixedString += charToFloat(ch);
                break;
            case 4: //Decimal numbers, after . before e
                float decNum = 0;
                float powOf = 1;

                //Divides by how far it is from decimal
                for (int i = 0; i < (lenPos - decPos); i++)
                {
                    powOf /= 10;
                }

                decNum = charToFloat(ch);
                decNum *= powOf;

                fixedString += decNum;
                break;
            case 6: //Number after the e
                fixedString *= 10;
                fixedString += charToFloat(ch);
                break;
        }

        return fixedString;
    }
}
