# CS 3110 Project 1 - Decimal Floating Point Literal
# By Laurence Timothy M. Garcia
The first project in CS 3110 involved creating a DFA system that recognizes any Java decimal floating point literals.

I started by drawing out a DFA, with the alphabet of the following symbols {d, t, e, f}. 
- 'd' represents digits
- 't' represents the decimal dot '.'
- 'e' represents the exponent part
- 'f' represents the float or double type suffix, such as 'f' or 'd'

The DFA that I created comprises of 7 states, with one being a trash state. The following image is the DFA I created.

Using the Python Programming Language, I developed this DFA into an actual program using a while loop that would repeat the function dfa_valid().
Inside this function holds all of the states of the DFA through a for loop that tests each character in the string through series of if-else statements that will
validate the string and convert it into a floating point literal.
