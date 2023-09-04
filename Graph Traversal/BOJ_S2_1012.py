import sys
from collections import deque

# 230904


def getAnswer():
    m, n, k = map(int, sys.stdin.readline().split())
    arr = [[0 for _ in range(n)] for _ in range(m)]  # 밭 배열
    count = 0

    # 배추 배치
    for _ in range(k):
        x, y = map(int, sys.stdin.readline().split())
        arr[x][y] = 1

    # 밭을 전체탐색하되, 배추가 있는 경우 bfs
    for i in range(m):
        for j in range(n):

            if arr[i][j] == 1:
                count += 1

                deq = deque()
                deq.append((i, j))

                while len(deq) != 0:
                    now = deq.popleft()
                    arr[now[0]][now[1]] = 0

                    # 아래
                    if now[0] != m - 1 and arr[now[0] + 1][now[1]] == 1:
                        arr[now[0] + 1][now[1]] = 0
                        deq.append((now[0] + 1, now[1]))

                    # 위
                    if now[0] != 0 and arr[now[0] - 1][now[1]] == 1:
                        arr[now[0] - 1][now[1]] = 0
                        deq.append((now[0] - 1, now[1]))

                    # 왼쪽
                    if now[1] != 0 and arr[now[0]][now[1] - 1] == 1:
                        arr[now[0]][now[1] - 1] = 0
                        deq.append((now[0], now[1] - 1))

                    # 오른쪽
                    if now[1] != n - 1 and arr[now[0]][now[1] + 1] == 1:
                        arr[now[0]][now[1] + 1] = 0
                        deq.append((now[0], now[1] + 1))

    print(count)


for _ in range(int(sys.stdin.readline())):
    getAnswer()


## 상하좌우 좌표를 접근해야 할 때의 접근법

# def solution(n, m, board):
#     cnt = 0
#     d = [(-1, 0), (1, 0), (0, -1), (0, 1)]
#     for i in range(n):
#         for j in range(m):
#             if board[i][j] == 0:
#                 continue
#             else:
#                 board[i][j] = 0
#                 stck = [(i, j)]
#                 while stck:
#                     now = stck.pop()
#                     for elem in d:
#                         if 0 <= now[0] + elem[0] < n and 0 <= now[1] + elem[1] < m:
#                             if board[now[0] + elem[0]][now[1] + elem[1]] == 1:
#                                 stck.append((now[0] + elem[0], now[1] + elem[1]))
#                                 board[now[0] + elem[0]][now[1] + elem[1]] = 0
#                 cnt += 1
#     return cnt

# 위와 같이 한칸 이동하는 튜플 값 Array 를 만들고 // d = [(-1, 0), (1, 0), (0, -1), (0, 1)]
# for 문으로 x,y 값 계산했을 시, 범위 내에 있는지만 비교 // for elem in d: if 0 <= now[0] + elem[0] < n and 0 <= now[1] + elem[1] < m:
