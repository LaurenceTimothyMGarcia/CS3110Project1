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
    array_len = input_len - 1       #provides length of string with only numbers starting from 0
    dec_float_point = 0             #Float Point
    pow_10 = 0                      #Power of 10 to multiply by

    char_pos = 0                    #Character position in string
    dec_pos = user_input.find('.')  #Location of decimal dot
    dec_check = False               #Boolean for the decimal dot

    exp_pos = user_input.find('e')  #Location of e
    exp_count = user_input.count('e')
    exp_check = False               #Boolean for e
    exp_neg_check = False           #Boolean for if e is negative
    exp_float = 0                   #Number of times 10 is multiplied by

    space_count = user_input.count("_") #counts number of white spaces
    f_count = user_input.count("f") #counts f
    d_count = user_input.count("d") #counts d

    if dec_pos == -1 and exp_pos == -1 and (user_input[input_len - 1] != 'f' and user_input[input_len - 1] != 'd'):
        print("Not a valid input")
        return
    
    if user_input[input_len - 1] == 'e' or user_input[input_len - 1] == '_':
        print("Not a valid input")
        return
    
    #Adjusts input length to account for space being removed
    array_len = array_len - (space_count + exp_count + f_count + d_count)
    print(array_len)

    for chr in user_input:
        print("Char Pos:", char_pos)
        print("Character is:", chr)

        #State 1
        #Checks if first input is either digit or decimal dot
        if char_pos == 0:
            if chr == 'e' or chr == '_' or chr == 'f' or chr == 'd':
                print("Not a valid input")
                return
            if chr not in translate.keys() and chr != '.':
                print("Not a valid input")
                return
        
        #Checks for underscores
        if chr == "_":
            char_pos += 1
            continue
            

        #State 7
        if chr == 'e':
            print(user_input[char_pos + 1])
            print(user_input[char_pos - 1])
            if user_input[char_pos + 1] == '_' or user_input[char_pos - 1] == '_':
                print("Not a valid input")
                return
            if user_input[char_pos + 1] == '-': #Checks for Negative
                exp_neg_check = True
            if exp_check:
                print("Not a valid input")
                return
            else:
                char_pos += 1
                exp_check = True
                print(exp_check)
                continue

        #State 3
        if chr == '.':
            if array_len <= 0:
                print("Not a valid input")
                return
            if dec_pos == (array_len):
                if user_input[char_pos - 1] == '_':
                    print("Not a valid input")
                    return
            else:
                if user_input[char_pos + 1] == '_' or user_input[char_pos - 1] == '_':
                    print("Not a valid input")
                    return
            if dec_check:
                print("Not a valid input")
                return
            else:
                char_pos += 1
                dec_check = True
                continue
        
        #State 10
        #Checks for float or double
        if chr == 'f' or chr == 'd':
            if user_input[char_pos] == '_':
                print("Not a valid input")
                return
            print("char pos:", char_pos)
            print("String len:", (input_len))
            if char_pos != (input_len - 1):
                print("Not a valid input")
                return
            else:
                continue

        #Trash State
        if chr not in translate.keys():
            print("Not a valid input")
            return

        #If there is no decimal
        if dec_pos == -1:
            pow_10 = (array_len) - char_pos
        #State 5
        #Numbers after Decimal
        elif char_pos > dec_pos and char_pos < exp_pos:
            pow_10 = dec_pos - char_pos
        #State 2
        #Numbers before decimal
        elif char_pos < dec_pos:
            pow_10 = dec_pos - char_pos - 1
        
        #State 8
        #Deals with exponent power of 10
        if exp_check:
            pow_10 = input_len - char_pos - 1
            print("Power of Exponent:", pow_10)
            exp_float += (translate[chr] * (10 ** pow_10))
            print("E float:", exp_float)
            if char_pos == (array_len):
                dec_float_point *= (10 ** exp_float)
            char_pos += 1
            continue
        
        dec_float_point += (translate[chr] * (10 ** pow_10))
        
        char_pos += 1

        #Checks for testing
        print("String character:", chr)
        print("Dictionary equiv:", translate[chr])
        print("Float Number:", dec_float_point)
    
    print("Final Float Number:", dec_float_point)
    num_dict.close()
    return


#Running Code
main()