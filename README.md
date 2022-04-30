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

When translating the Pushdown Automata to code, I continued to use Java as my programming language, in order to take advantage of the switch statements. The code for Project 2 can be found in **TextBasedCalc.java**. It starts off similar to the DFA as the program takes the string the user entered and places it in a while loop iterating through each character in the string, however there are 2 stacks added now. 

The first stack was floatStack and it held all floats from the string. The second stack was operatorStack, which held all the operators and parentheses. I created a void method called pemdasStack() which would take the operators and floats and use the PEMDAS procedure to calculate the float with each step. The PEMDAS method would pop the first 2 floats then do the following operation on them. It got complicated with parentheses as they had a different order, but I managed to make it work. When pushing floats onto the floatStack, I readjusted my addTotal() method from the DFA project and changed it to the method floatCreation(). I also created a new method called floatExponentAdd() which was from some code in the DFA project at the end that would add the exp value if it existed to the float. Once these two occurred, I would push the float onto the floatStack. There are some issues with precision when going to smaller decimal numbers with an example being .0001 being 9.999e-5 but overall the program works as planned.
