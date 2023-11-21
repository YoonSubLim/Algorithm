import sys
from collections import deque

# 231121
N, M = map(int, sys.stdin.readline().split())

isFriend = [[False] * N for _ in range(N)]
bacons = []

# friend 행렬
for _ in range(M):
    A, B = map(int, sys.stdin.readline().split())
    isFriend[A-1][B-1] = isFriend[B-1][A-1] = True

# 유저별 베이컨 수 구하기
for userIdx in range(N):
    distant = [-1] * N

    deq = deque()
    deq.append((userIdx, 0))
    distant[userIdx] = 0

    while deq:
        current = deq.popleft()
        dist = current[1] + 1

        for i in range(N):
            if isFriend[current[0]][i] and distant[i] == -1:
                deq.append((i, dist))
                distant[i] = dist

    bacons.append(sum(distant))

# 결과 출력
print(bacons.index(min(bacons)) + 1)
