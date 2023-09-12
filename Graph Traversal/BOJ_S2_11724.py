import sys
from collections import deque

# 230911

N, M = map(int, sys.stdin.readline().split())
matrix = [[0 for _ in range(N)] for _ in range(N)]
visited = [False for _ in range(N)]

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    matrix[a-1][b-1] = matrix[b-1][a-1] = 1

count = 0
deq = deque()
for i in range(N):
    if not visited[i]:
        visited[i] = True
        deq.append(i)
        count += 1

    while len(deq) != 0:
        now = deq.popleft()
        for j in range(N):
            if matrix[now][j] == 1 and not visited[j]:
                deq.append(j)
                visited[j] = True

print(count)