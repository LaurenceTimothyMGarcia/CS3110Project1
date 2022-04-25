# CS 3110 Project 1 - Decimal Floating Point Literal
# By Laurence Timothy M. Garcia
The first project in CS 3110 involved creating a DFA system that recognizes any Java decimal floating point literals.

# DFA
I started by drawing out a DFA, with the alphabet of the following symbols {d, t, e, f, u, m}. 
- 'd' represents digits 0-9
- 't' represents the decimal dot '.'
- 'e' represents the exponent part
- 'f' represents the float or double type suffix, such as 'f' or 'd'
- 'u' represents spaces/underscores
- 'm' represents the plus or minus sign after the exponent e part

The DFA that I created comprises of 12 states, with one being a trash state and 4 accept states. The following image is the DFA I created.
![alt text](https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/main/Images/DFA_Drawn.jpg)
Same DFA recreated in JFlaps, which the file for can be found here: https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/main/CS3110Project1.jff

![alt text](https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/main/Images/DFA_JFLAPS.png)

# Programming the DFA
When translating the DFA to an actual program, I have come to realize that **Java** would work best as it is a language that features switch statements that I am most familiar with. The code for Project 1 can be found in **TextBasedCalc.java**. During a while loop, there will be a switch statement that tracks which case the character of the string is in, then within those switch statements, there is another set of switch statements that check for each symbol or character and changes the case and calculates accordingly.

Code TextBasedCalc.java: https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/main/TextBasedCalc.java

The characters of the string would be translated into a float value by a switch statement in the method charToFloat(). It just compares the character in any of the digits from 0-9 then returns the float equivalent of that character.

When calculating the string to a float, it would keep track of the state it is in and calculate accordingly in a method called addTotal(). For example, if the current state was 2, it would be before the decimal, so it would just multiply by 10 then add the current character in as a float. If the state was 4, it would be a decimal value, so it would get a negative power of 10 depending on how far the character was from the decimal, then multiply that to the character as a float, then add it to the final string. If the state was e, it would do the same as state 2, but instead place it into its own exponent variable then multiply it at the end.

# Initial Attempts in Programming
Originally I used the Python Programming Language and developed this DFA into an actual program using a while loop that would repeat the function dfa_valid().
Inside this function holds all of the states of the DFA through a for loop that tests each character in the string through series of if-else statements that will
validate the string and convert it into a floating point literal.

Initially, I started with the Python file CalcMain.py, but made too many errors on it, so I started fresh with DecFloatLit.py. I also created a .JSON dictionary that 
contains all of the strings that can be accepted and translates string numbers to integers, so the program only needs to reference the dictionary if it exists. However this method was also very inconsistent and was very messy in terms of code. While it worked, it was not sustainable for the second part of the project. So I redid the project in Java. I will quote my old thought process with the Python code was below.

>The code runs a check on the first string to see if it follows a decimal dot or digits 0-9, then proceeds to check the rest of the string one character at a time.
>Each character runs through a series of if-else statements that can turn on several booleans and int counters to determine which state the string is in. At the end,
>the number will find its placement by referencing its placement relative to the decimal dot and be added to a sum float at the end. Afterwards, it moves to the next
>character. When testing the inputs, it prompts the user to input a string, and if it is valid, it will repeat that value. However if it is not valid, it will respond, "input not valid."
