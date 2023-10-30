import sys
from collections import deque

# 231030

directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]

N, M = map(int, sys.stdin.readline().split())
matrix = []
deq = deque()

result = 0

for i in range(N):
    line = list(sys.stdin.readline().strip())
    if 'I' in line:
        startX = i
        startY = line.index('I')
        deq.append((startX, startY))

    matrix.append(line)

while deq:
    current = deq.popleft()

    for direction in directions:
        next = (current[0] + direction[0], current[1] + direction[1])

        if not (0 <= next[0] < N and 0 <= next[1] < M):
            continue

        if matrix[next[0]][next[1]] == 'P':
            matrix[next[0]][next[1]] = 'I'
            deq.append((next[0], next[1]))
            result += 1

        if matrix[next[0]][next[1]] == 'O':
            matrix[next[0]][next[1]] = 'I'
            deq.append((next[0], next[1]))

if result == 0:
    print("TT")
else:
    print(result)
