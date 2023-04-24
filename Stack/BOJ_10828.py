import sys

# 230420

n = input()

stck = list()

for _ in range(int(n)):
    word = sys.stdin.readline().strip()
    if word.split()[0] == "push":
        stck.append(word.split()[1])
    elif word == "pop":
        if len(stck) == 0:
            print("-1")
        else:
            print(stck.pop())
    elif word == "size":
        print(len(stck))
    elif word == "empty":
        if len(stck) == 0:
            print(1)
        else:
            print(0)
    elif word == "top":
        if len(stck) == 0:
            print("-1")
        else:
            print(stck[-1])
