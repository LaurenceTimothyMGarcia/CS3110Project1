# CS 3110 Project 2 - Decimal Floating Point Expressions
# By Laurence Timothy M. Garcia
The second project in CS 3110 involved creating a PDA system that recognizes any Java decimal floating point expressions.

# Programming the PDA
Code FloatPointExpressionPDA.java: https://github.com/LaurenceTimothyMGarcia/CS3110Project1/blob/Project2/FloatPointExpressionPDA.java

When translating the Pushdown Automata to code, I continued to use Java as my programming language, in order to take advantage of the switch statements. The code for Project 2 can be found in **TextBasedCalc.java**. It starts off similar to the DFA as the program takes the string the user entered and places it in a while loop iterating through each character in the string, however there are 2 stacks added now. 

The first stack was floatStack and it held all floats from the string. The second stack was operatorStack, which held all the operators and parentheses. I created a void method called pemdasStack() which would take the operators and floats and use the PEMDAS procedure to calculate the float with each step. The PEMDAS method would pop the first 2 floats then do the following operation on them. It got complicated with parentheses as they had a different order, but I managed to make it work. When pushing floats onto the floatStack, I readjusted my addTotal() method from the DFA project and changed it to the method floatCreation(). I also created a new method called floatExponentAdd() which was from some code in the DFA project at the end that would add the exp value if it existed to the float. Once these two occurred, I would push the float onto the floatStack. There are some issues with precision when going to smaller decimal numbers with an example being .0001 being 9.999e-5 but overall the program works as planned.
