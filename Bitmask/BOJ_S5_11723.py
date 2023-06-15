
# 230614

import sys
input = sys.stdin.readline

my_set = set()

for _ in range(int(input())):
    op = input().split()

    if len(op) > 1:
        op[1] = int(op[1])

        if op[0] == 'add':
            my_set.add(op[1])
        elif op[0] == 'remove':
            my_set.discard(op[1])
        elif op[0] == 'check':
            if op[1] in my_set:
                print(1)
            else:
                print(0)
        elif op[0] == 'toggle':
            if op[1] in my_set:
                my_set.remove(op[1])
            else:
                my_set.add(op[1])
    else:
        if op[0] == 'all':
            my_set = {i+1 for i in range(20)}
        elif op[0] == 'empty':
            my_set.clear()
