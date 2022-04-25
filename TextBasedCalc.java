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
        int stringSize = inputString.length();
        int lenPos = 0;

        float fixedString = 0;
        int currentState = 1;

        while (lenPos < stringSize)
        {
            char ch = inputString.charAt(lenPos);

            switch currentState
            {
                case 1: //State 1
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
                case 2: //State 2
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
                case 0: //Trash State
                    break;
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
}