#Laurence Timothy M. Garcia
#CS 3110
#Professor Dong
#April 13th, 2022

import json

def main():
    user_input = input("Input String here (Type in exit(0) to quit): ")

    while user_input != "exit(0)":
        dfa_valid(user_input)
        user_input = input("Input String here (Type in exit(0) to quit): ")

def dfa_valid(user_input):
    num_dict = open('NumAlpha.json')
    translate = json.load(num_dict)

    user_input = user_input.lower() #changes everything to lower case
    input_len = len(user_input)     #provides length of string
    char_pos = 0                     #Character position in string

    for chr in user_input:
        print("Char Pos:", char_pos)
        print("Character is:", chr)

        #State 1
        #Checks if first input is either digit or decimal dot
        if char_pos == 0:
            if chr not in translate.keys() and chr != '.':
                print("Not a valid input")
                return
        

        
        char_pos += 1
    
    num_dict.close()
    return


#Running Code
main()