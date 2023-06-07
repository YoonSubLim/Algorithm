
# 230607

import sys
input = sys.stdin.readline


while True:
    word = input()
    li = ['@']

    if word[0] == '.':
        break

    for ch in word:
        if ch == '.':
            if len(li) != 1:
                print("no")
            else:
                print("yes")
            break
        elif ch == '[':
            li.append('[')
        elif ch == '(':
            li.append('(')
        elif ch == ']':
            if li[-1] != '[':
                print("no")
                break
            else:
                li.pop()
        elif ch == ')':
            if li[-1] != '(':
                print("no")
                break
            else:
                li.pop()
