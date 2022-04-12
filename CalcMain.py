#Laurence Timothy M. Garcia
#CS 3110
#Professor Dong
#April 13th, 2022

import json

def main():
    user_input = input("")

    convert_string(user_input)

    '''
    while user_input != 'q':
        valid_number = input_valid(user_input)
        user_input = input("")
    '''

def input_valid(user_input):
    user_input = user_input.lower()
    input_length = len(user_input)
    exp_place = user_input.find('e')

    if user_input[-1] == 'd' or user_input[-1] == 'f':
        user_input = user_input[:input_length-1]
    else:
        print("This is not a literal.")
        return

    if exp_place != -1:
        string_split = user_input.split('e')
        number = float(string_split[0])
        exponent = float(string_split[1])

        exponent = 10 ** exponent
        user_input = number * exponent

    print(user_input)
    return user_input

def convert_string(string):
    num_dict = open('NumAlpha.json')
    translate = json.load(num_dict)

    str_len = len(string)
    str_pos = 0
    dec_loc = 0
    exp_loc = 0
    string = string.lower()

    for i in string:
        if i == '.':
            dec_loc = str_pos
            print(dec_loc)
        if i == 'e':
            exp_loc = str_pos
            print(exp_loc)
        str_pos += 1

    num_dict.close()

main()