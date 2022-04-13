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
    dec_float_point = 0             #Float Point

    char_pos = 0                    #Character position in string
    dec_pos = user_input.find('.')  #Location of decimal dot
    dec_check = False               #Boolean for the decimal dot
    exp_check = False               #Boolean for e

    pow_10 = 0                      #Power of 10 to multiply by

    for chr in user_input:
        print("Char Pos:", char_pos)
        print("Character is:", chr)

        #State 1
        #Checks if first input is either digit or decimal dot
        if char_pos == 0:
            if chr == 'e':
                print("Not a valid input")
                return
            if chr not in translate.keys() and chr != '.':
                print("Not a valid input")
                return

        #State 3
        if chr == '.':
            if dec_check:
                print("Not a valid input")
                return
            else:
                char_pos += 1
                dec_check = True
                continue

        if dec_check and not exp_check:
            pow_10 = dec_pos - char_pos
        elif not dec_check and not exp_check:
            pow_10 = dec_pos - char_pos - 1
        
        

        dec_float_point += (translate[chr] * (10 ** pow_10))
        
        char_pos += 1

        #Checks for testing
        print("String character:", chr)
        print("Dictionary equiv:", translate[chr])
        print("Float Number:", dec_float_point)
    
    num_dict.close()
    return


#Running Code
main()