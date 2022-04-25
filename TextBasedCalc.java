//Laurence Timothy M. Garcia
//CS 3110
//Professor Qichao Dong
//May 2nd, 2022

import java.util.Scanner;

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

        while (lenPos < stringSize)
        {
            char ch = inputString.charAt(lenPos);

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
                            currentState = 2;
                            break;
                        case '.':
                            currentState = 3;
                            break;
                        default:
                            currentState = 0;
                            break;
                    }
                    break;
                
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
                    break;
                
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
                    break;

                //State 4 - After decimal before E
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
                    break;

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
                    break;

                //Trash State
                case 0:
                    System.out.println("Input not valid");
                    break;
            }

            lenPos++;
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
}