import sys
from collections import deque

# 231019

# 익은 토마토(1) 인 좌표들을 처음에 deque 에 저장. 이때 len(deque) = k 라 하자.
# k 번 popleft 할 때, 상하좌우 중 방문하지 않았고, 안 익은 토마토(0) 를 deque 에 추가 -> k번 popleft 시 day += 1
# deq 에 아무 좌표도 없으면 더이상 익을 토마토는 존재하지 않고, 출력할 값만 판단해주면 됨
# -> 0이 존재(모두가 익지는 않음) : -1 / 그 외 : day 출력

M, N = map(int, sys.stdin.readline().split())

box = []
visited = [[False for _ in range(M)] for _ in range(N)]

deq = deque()

# 상하좌우
dirX = [-1, 1, 0, 0]
dirY = [0, 0, -1, 1]

for r in range(N):
    row = list(map(int, sys.stdin.readline().split()))

    for c in range(M):
        if row[c] == -1 or row[c] == 1:
            visited[r][c] = True
            if row[c] == 1:
                deq.append([r, c])

    box.append(row)

day = -1
# 하루에 popleft 해야할 토마토의 수
tomatoOfDay = len(deq)

while deq:
    [x, y] = deq.popleft()

    for i in range(4):
        [nx, ny] = [a + b for a, b in zip([x, y], [dirX[i], dirY[i]])]

        if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny]:
            deq.append([nx, ny])
            visited[nx][ny] = True

    tomatoOfDay -= 1
    if tomatoOfDay == 0:
        day += 1
        tomatoOfDay = len(deq)

if any(False in inner for inner in visited):
    print(-1)
else:
    print(day)
