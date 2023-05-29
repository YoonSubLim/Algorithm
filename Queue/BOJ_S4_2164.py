
# 230529

import sys

from collections import deque

n = int(sys.stdin.readline())

deq = deque()

for i in range(n):
    deq.append(i+1)

while len(deq) != 1:
    deq.popleft()
    deq.rotate(-1) # <- 방향으로 회전

print(deq.pop())