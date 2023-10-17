import sys
from collections import deque

# 231017

N, K = map(int, sys.stdin.readline().split())

# DP 방식 _ 좋은 방식이 아닌 것 같음

# if K <= N:
#     print(N - K)
#     exit(0)
#
# dp = [abs(N - i) for i in range(100001)]
# for i in range(1, 50001):
#     idx = 2 * i
#     while idx < 100001:
#         dp[idx] = min(dp[idx], dp[idx // 2] + 1)
#         dp[idx - 1] = min(dp[idx - 1], dp[idx] + 1)
#         if idx < 100000:
#             dp[idx + 1] = min(dp[idx + 1], dp[idx] + 1)
#
#         idx *= 2
#
# print(dp[K])


# BFS 방식
# depth 가 적을 수록 최소 거리이기 때문에, cur==k 일 때의 distant 값이 최소이다.
# https://wook-2124.tistory.com/273 의 그림 참고
deq = deque()
distant = [-1] * 100001

deq.append(N)
distant[N] = 0

while deq:
    cur = deq.popleft()

    if cur == K:
        print(distant[K])
        break

    if cur - 1 >= 0 and distant[cur - 1] == -1:
        deq.append(cur - 1)
        distant[cur - 1] = distant[cur] + 1

    if cur + 1 < 100001 and distant[cur + 1] == -1:
        deq.append(cur + 1)
        distant[cur + 1] = distant[cur] + 1

    if cur * 2 < 100001 and distant[cur * 2] == -1:
        deq.append(cur * 2)
        distant[cur * 2] = distant[cur] + 1
