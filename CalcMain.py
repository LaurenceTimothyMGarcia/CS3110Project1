#Laurence Timothy M. Garcia
#CS 3110
#Professor Dong
#April 13th, 2022

def main():
    user_input = input("")

    input_valid(user_input)

def input_valid(user_input):
    user_input = user_input.lower()
    input_length = len(user_input)

    if user_input[-1] == 'd' or user_input[-1] == 'f':
        user_input = user_input[:input_length-1]
    
    print(user_input)

main()