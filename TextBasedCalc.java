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
        inputString = inputString.toLowerCase();
        int stringSize = inputString.length();
        int lenPos = 0;

        float fixedString = 0;
        int currentState = 1;

        int decPos = 0;
        int expPos = 0;

        //Looping through for validation
        //Using label to break loop in switch statement
        while (inputString != "q")
        {
            loopValid: while (lenPos < stringSize)
            {
                char ch = inputString.charAt(lenPos);
                System.out.println("Character: " + ch);
                lenPos++;

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
                                fixedString = charToFloat(ch);
                                System.out.println("Fixed String: " + fixedString);
                                currentState = 2;
                                break;
                            case '.':
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
                                currentState = 2;
                                break;
                            case '.':
                                currentState = 3;
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
                                break;
                            case 'e':
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
                                break;
                            case 'e':
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
                                break;
                            case '+':
                            case '-':
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
                                break;
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
        }
        

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
}