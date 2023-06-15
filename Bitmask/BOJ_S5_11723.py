
# 230615

import sys
input = sys.stdin.readline

my_set = 0

for _ in range(int(input())):
    op = input().split()

    if len(op) > 1:
        op[1] = int(op[1])
        if op[0] == 'add':
            my_set = my_set | (1 << (20-op[1]))
        elif op[0] == 'remove':
            my_set = my_set & ~(1 << (20-op[1]))
        elif op[0] == 'check':
            check = my_set & (1 << (20-op[1]))
            print(1 if check != 0 else 0)
        elif op[0] == 'toggle':
            my_set = my_set ^ (1 << (20-op[1]))
    else:
        if op[0] == 'all':
            my_set = (1 << 21) - 1
        elif op[0] == 'empty':
            my_set = 0
