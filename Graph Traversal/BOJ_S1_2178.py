from collections import deque

# 231122

N, M = map(int, input().split())
mat = []
direction = [(1, 0), (-1, 0), (0, 1), (0, -1)]

for _ in range(N):
    mat.append([int(ch) for ch in input()])

deq = deque()
deq.append((0, 0, 1))  # row col distant
mat[0][0] = 0

while deq:
    cur = deq.popleft()

    if cur[0] == N - 1 and cur[1] == M - 1:
        print(cur[2])
        break

    for direct in direction:
        newRow = cur[0] + direct[0]
        newCol = cur[1] + direct[1]
        if not (0 <= newRow < N and 0 <= newCol < M and mat[newRow][newCol] == 1):
            continue
        deq.append((newRow, newCol, cur[2] + 1))
        mat[newRow][newCol] = 0
