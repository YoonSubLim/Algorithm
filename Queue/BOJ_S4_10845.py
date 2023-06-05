
# 230605

import sys
from collections import deque

input = sys.stdin.readline

dq = deque()

for _ in range(int(input())):
    ops = input().split()

    if ops[0] == 'push':
        dq.append(ops[1])
    elif ops[0] == 'pop':
        if len(dq) == 0:
            print(-1)
        else:
            print(dq.popleft())
    elif ops[0] == 'size':
        print(len(dq))
    elif ops[0] == 'empty':
        if len(dq) == 0:
            print(1)
        else:
            print(0)
    elif ops[0] == 'front':
        if len(dq) == 0:
            print(-1)
        else:
            print(dq[0])
    elif ops[0] == 'back':
        if len(dq) == 0:
            print(-1)
        else:
            print(dq[-1])
