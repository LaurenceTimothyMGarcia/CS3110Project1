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
- 'o' represents operators +, -, *, /
- '(' represents left parentheses
- ')' represents right parentheses
- 's' represents white space

The second one is a simplier design assuming the float is valid, with an alphabet of the following symbols {d, o, s, (, )}
- 'd' represents a valid float
- 'o' represents operators +, -, *, /
- '(' represents left parentheses
- ')' represents right parentheses
- 's' represents white space

The first PDA that I created comprises of 17 states, with 1 accept state. The following image is the first PDA I created.
![alt text](https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/Project2/Images/PDA1_Drawn.jpg)

The second PDA I created comprises of 8 states, with 1 accept state. The following image is the second PDA I created.
![alt text](https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/Project2/Images/PDA2_Drawn.jpg)

# Programming the PDA
Code FloatPointExpressionPDA.java: https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/Project2/FloatPointExpressionPDA.java



# Initial Attempts in Programming

