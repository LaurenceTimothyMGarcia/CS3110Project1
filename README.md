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

# Programming
Using the Python Programming Language, I developed this DFA into an actual program using a while loop that would repeat the function dfa_valid().
Inside this function holds all of the states of the DFA through a for loop that tests each character in the string through series of if-else statements that will
validate the string and convert it into a floating point literal.

Initially, I started with the Python file CalcMain.py, but made too many errors on it, so I started fresh with DecFloatLit.py. I also created a .JSON dictionary that 
contains all of the strings that can be accepted and translates string numbers to integers, so the program only needs to reference the dictionary if it exists.

The code runs a check on the first string to see if it follows a decimal dot or digits 0-9, then proceeds to check the rest of the string one character at a time.
Each character runs through a series of if-else statements that can turn on several booleans and int counters to determine which state the string is in. At the end,
the number will find its placement by referencing its placement relative to the decimal dot and be added to a sum float at the end. Afterwards, it moves to the next
character.

When testing the inputs, it prompts the user to input a string, and if it is valid, it will repeat that value. However if it is not valid, it will respond, "input not valid."
