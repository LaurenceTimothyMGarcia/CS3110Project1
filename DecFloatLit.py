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
    pow_10 = 0                      #Power of 10 to multiply by

    char_pos = 0                    #Character position in string
    dec_pos = user_input.find('.')  #Location of decimal dot
    dec_check = False               #Boolean for the decimal dot

    exp_pos = user_input.find('e')  #Location of e
    exp_check = False               #Boolean for e
    exp_float = 0                   #Number of times 10 is multiplied by

    space_count = user_input.count("_") #counts number of white spaces

    if dec_pos == -1 and exp_pos == -1 and (user_input[input_len - 1] != 'f' and user_input[input_len - 1] != 'd'):
        print("Not a valid input")
        return
    
    if user_input[input_len - 1] == 'e':
        print("Not a valid input")
        return
    
    #Adjusts input length to account for space being removed
    input_len = input_len - space_count
    print(input_len)

    for chr in user_input:
        print("Char Pos:", char_pos)
        print("Character is:", chr)

        #State 1
        #Checks if first input is either digit or decimal dot
        if char_pos == 0:
            if chr == 'e' or chr == '_':
                print("Not a valid input")
                return
            if chr not in translate.keys() and chr != '.':
                print("Not a valid input")
                return
        
        #Checks for underscores
        if chr == "_":
            continue
            

        #State 5
        if chr == 'e':
            if user_input[char_pos + 1] == '_' or user_input[char_pos - 1] == '_':
                print("Not a valid input")
                return
            if exp_check:
                print("Not a valid input")
                return
            else:
                char_pos += 1
                exp_check = True
                continue

        #State 3
        if chr == '.':
            if user_input[char_pos + 1] == '_' or user_input[char_pos - 1] == '_':
                print(user_input[char_pos + 1])
                print(user_input[char_pos - 1])
                print("Not a valid input")
                return
            if dec_check:
                print("Not a valid input")
                return
            else:
                char_pos += 1
                dec_check = True
                continue
        
        #State 6
        #Checks for float or double
        if chr == 'f' or chr == 'd':
            if user_input[char_pos + 1] == '_' or user_input[char_pos - 1] == '_':
                print("Not a valid input")
                return
            print("char pos:", char_pos)
            print("String len:", (input_len - 1))
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
            pow_10 = (input_len - 2) - char_pos
        #State 4
        #Numbers after Decimal
        elif dec_check and not exp_check:
            pow_10 = dec_pos - char_pos
        #State 2
        #Numbers before decimal
        elif not dec_check and not exp_check:
            pow_10 = dec_pos - char_pos - 1
        #State 5 cont.
        #Deals with exponent power of 10
        elif exp_check:
            pow_10 = input_len - char_pos - 1
            print(pow_10)
            exp_float += (translate[chr] * (10 ** pow_10))
            print("E float:", exp_float)
            if char_pos == (input_len - 1):
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