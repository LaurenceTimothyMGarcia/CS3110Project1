# CS 3110 Project 2 - Decimal Floating Point Expressions
# By Laurence Timothy M. Garcia
The second project in CS 3110 involved creating a PDA system that recognizes any Java decimal floating point expressions.

# PDA
I started by drawing out 2 PDAs. 

The first one has float validation from the DFA, with the alphabet of the following symbols {d, t, e, f, u, m}. 
- 'd' represents digits 0-9
- 't' represents the decimal dot '.'
- 'e' represents the exponent part
- 'f' represents the float or double type suffix, such as 'f' or 'd'
- 'u' represents spaces/underscores
- 'm' represents the plus or minus sign after the exponent e part

The second one is a simplier design without the float validation of the DFA, with an alphabet of the following symbols

The PDA that I created comprises of 12 states, with one being a trash state and 4 accept states. The following image is the PDA I created.
![alt text](https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/main/Images/DFA_Drawn.jpg)
Same PDA recreated in JFlaps, which the file for can be found here: https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/main/CS3110Project1.jff

![alt text](https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/main/Images/DFA_JFLAPS.png)

# Programming the PDA
Code FloatPointExpressionPDA.java: 



# Initial Attempts in Programming

