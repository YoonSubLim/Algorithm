import sys
from collections import deque

# 231018

N, M = map(int, sys.stdin.readline().split())

deq = deque()

# map_matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
map_matrix = []
visited = [list(False for i in range(M)) for j in range(N)]

for r in range(N):
    # matrix 에 값을 넣으면서, 동시에 목적지 deque append 작업 수행
    map_matrix.append(list(map(int, sys.stdin.readline().split())))

    if 2 in map_matrix[r]:
        c = map_matrix[r].index(2)
        deq.append((r, c))
        map_matrix[r][c] = 0
        visited[r][c] = True


while deq:
    cur = deq.popleft()
    cur_dist = map_matrix[cur[0]][cur[1]]

    # Up
    if cur[0] - 1 >= 0 and map_matrix[cur[0] - 1][cur[1]] == 1 and not visited[cur[0] - 1][cur[1]]:
        map_matrix[cur[0] - 1][cur[1]] = cur_dist + 1
        visited[cur[0] - 1][cur[1]] = True
        deq.append((cur[0] - 1, cur[1]))

    # Down
    if cur[0] + 1 < N and map_matrix[cur[0] + 1][cur[1]] == 1 and not visited[cur[0] + 1][cur[1]]:
        map_matrix[cur[0] + 1][cur[1]] = cur_dist + 1
        visited[cur[0] + 1][cur[1]] = True
        deq.append((cur[0] + 1, cur[1]))

    # LEFT
    if cur[1] - 1 >= 0 and map_matrix[cur[0]][cur[1] - 1] == 1 and not visited[cur[0]][cur[1] - 1]:
        map_matrix[cur[0]][cur[1] - 1] = cur_dist + 1
        visited[cur[0]][cur[1] - 1] = True
        deq.append((cur[0], cur[1] - 1))

    # RIGHT
    if cur[1] + 1 < M and map_matrix[cur[0]][cur[1] + 1] == 1 and not visited[cur[0]][cur[1] + 1]:
        map_matrix[cur[0]][cur[1] + 1] = cur_dist + 1
        visited[cur[0]][cur[1] + 1] = True
        deq.append((cur[0], cur[1] + 1))


for i in range(N):
    for j in range(M):
        if not visited[i][j] and map_matrix[i][j] == 1:
            map_matrix[i][j] = -1

for li in map_matrix:
    print(' '.join(map(str, li)))
