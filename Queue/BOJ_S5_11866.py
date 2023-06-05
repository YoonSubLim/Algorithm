
# 230605

import sys
from collections import deque
input = sys.stdin.readline

# N 명 중 K번째 제거 -> 제거되는 순서 print
N, K = map(int, input().split())

answer = []
dq = deque(range(1, N+1)) # 1~N 까지 포함된 deque

while len(dq) != 0:
    dq.rotate(-(K-1)) # K-1 왼쪽 회전 후 popleft 할 때의 사람이 K 번째임
    answer.append(dq.popleft())

print("<", end='')
for i in range(len(answer)-1):
    print(answer[i], end=', ')
print(answer[len(answer)-1], end='>')
