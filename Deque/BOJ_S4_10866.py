
# 230607

import sys
from collections import deque

input = sys.stdin.readline

dq = deque()

for i in range(int(input())):
    op = input().split()

    if op[0] == 'push_front':
        dq.append(op[1])
    elif op[0] == 'push_back':
        dq.appendleft(op[1])
    elif op[0] == 'pop_front':
        if len(dq) == 0:
            print(-1)
            continue
        print(dq.pop())
    elif op[0] == 'pop_back':
        if len(dq) == 0:
            print(-1)
            continue
        print(dq.popleft())
    elif op[0] == 'size':
        print(len(dq))
    elif op[0] == 'empty':
        if len(dq):
            print(0)
        else:
            print(1)
    elif op[0] == 'front':
        if len(dq) == 0:
            print(-1)
            continue
        print(dq[-1])
    elif op[0] == 'back':
        if len(dq) == 0:
            print(-1)
            continue
        print(dq[0])
