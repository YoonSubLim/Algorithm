
# 230619

import sys
input = sys.stdin.readline

N = int(input())

scores = []
dp = []

for i in range(N):
    scores.append(int(input()))
    if i >= 3:
        # N-3 / N-1 / N 의 경우와
        # N-2 / N 의 경우 중 더 큰 값
        dp.append(max((scores[i-1] + dp[i-3]), dp[i-2]) + scores[i])
    elif i <= 1:
        dp.append(sum(scores))
    elif i == 2:
        dp.append(max(scores[0], scores[1]) + scores[2])

print(dp[-1])
