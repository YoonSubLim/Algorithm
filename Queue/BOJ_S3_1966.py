
# 230602

import sys
from collections import deque
input = sys.stdin.readline

for _ in range(int(input())):
    N, M = map(int, input().split())
    deq = deque(map(int, input().split()))

    answer = 0
    while True:
        if deq[0] == max(deq):
            answer += 1
            if M == 0:
                print(answer)
                break
            else:
                deq.popleft()
                M -= 1
        else:
            deq.rotate(-1)
            M -= 1
            if M == -1:
                M = len(deq)-1

