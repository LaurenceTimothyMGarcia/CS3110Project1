//Laurence Timothy M. Garcia
//CS 3110
//Professor Qichao Dong
//May 2nd, 2022

import java.util.Scanner;
import java.util.*;

public class TextBasedCalc
{
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        String inputString = keyboardInput(kb);

        do
        {
            inputString = inputString.toLowerCase();
            int stringSize = inputString.length();

            float fixedString = 0;
            int currentState = 1;

            int decPos = 0;
            int expPos = 0;
            int lenPos = 0;

            float expValue = 0;
            boolean expNeg = false;

            //Looping through for validation
            //Using label to break loop in switch statement
            loopValid: while (lenPos < stringSize)
            {
                char ch = inputString.charAt(lenPos);
                System.out.println("Character: " + ch);
                lenPos++;

                System.out.println("State: " + currentState);

                switch (currentState)
                {
                    //State 1 - Starting state
                    case 1:
                        switch(ch)
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
                                fixedString = addTotal(lenPos, currentState, decPos, expPos, fixedString, ch);
                                currentState = 2;
                                break;
                            case '.':
                                decPos = lenPos;
                                currentState = 3;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue loopValid;
                    
                    //State 2 - Digit before decimals
                    case 2:
                        switch(ch)
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
                                fixedString = addTotal(lenPos, currentState, decPos, expPos, fixedString, ch);
                                currentState = 2;
                                break;
                            case '.':
                                decPos = lenPos;
                                currentState = 3;
                                break;
                            case 'e':
                                expPos = lenPos;
                                currentState = 5;
                                break;
                            case 'f':
                            case 'd':
                                currentState = 8;
                                break;
                            case '_':
                                currentState = 9;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue loopValid;
                    
                    //State 3 - receives decimal
                    case 3:
                        switch(ch)
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
                                fixedString = addTotal(lenPos, currentState, decPos, expPos, fixedString, ch);
                                break;
                            case 'e':
                                expPos = lenPos;
                                currentState = 5;
                                break;
                            case 'f':
                            case 'd':
                                currentState = 8;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue loopValid;

                    //State 4 - After digits before E
                    case 4:
                        switch(ch)
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
                                fixedString = addTotal(lenPos, currentState, decPos, expPos, fixedString, ch);
                                break;
                            case 'e':
                                expPos = lenPos;
                                currentState = 5;
                                break;
                            case 'f':
                            case 'd':
                                currentState = 8;
                                break;
                            case '_':
                                currentState = 10;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue loopValid;

                    //State 5 - E has been inputed
                    case 5:
                        switch(ch)
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
                                expValue = addTotal(lenPos, currentState, decPos, expPos, expValue, ch);
                                break;
                            case '-':
                                expNeg = true;
                            case '+':
                                currentState = 7;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue loopValid;
                    
                    //State 6 - digits after e
                    case 6:
                        switch(ch)
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
                                expValue = addTotal(lenPos, currentState, decPos, expPos, expValue, ch);
                                break;
                            case 'd':
                            case 'f':
                                currentState = 8;
                                break;
                            case '_':
                                currentState = 11;
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue loopValid;

                    //State 7 - looks for - or + after E
                    case 7:
                        switch(ch)
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
                                expValue = addTotal(lenPos, currentState, decPos, expPos, expValue, ch);
                                break;
                            default:
                                currentState = 0;
                                break;
                        }
                        continue loopValid;
                    
                    //State 8 - Receives f or d
                    case 8:
                        switch(ch)
                        {
                            default:
                                currentState = 0;
                                break;
                        }
                        continue loopValid;

                    //State 9, 10, 11 - underscore state
                    case 9:
                        switch(ch)
                        {
                            case '_':
                                currentState = 9;
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
                        continue loopValid;
                    case 10:
                        switch(ch)
                        {
                            case '_':
                                currentState = 10;
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
                        continue loopValid;
                    case 11:
                        switch(ch)
                        {
                            case '_':
                                currentState = 11;
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
                        continue loopValid;

                    //Trash State
                    case 0:
                        System.out.println("Input not valid");
                        break loopValid;
                }
            }

            switch (currentState)
            {
                case 3:
                case 4:
                case 6:
                case 8:
                    if (expNeg)
                    {
                        expValue *= -1;
                    }
        
                    System.out.println("EXP value: " + expValue);
        
                    if (expValue != 0)
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
        
                        fixedString *= exp10;
                    }
        
                    System.out.println("Final Number: " + fixedString);
                    break;
                
                default:
                    System.out.println("Input not valid");
                    break;
            }

            inputString = keyboardInput(kb);
        }   while (inputString != "q");
        

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

    public static float addTotal(int lenPos, int state, int decPos, int expPos, float fixedString, char ch)
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

        System.out.println("Fixed String: " + fixedString);
        return fixedString;
    }
}