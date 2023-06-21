
# 230621

# [11726] 2xN 타일링
# https://www.acmicpc.net/board/view/71651
# 의 연장선

n = int(input())

dp = [0]
dp.append(1)
dp.append(3)

# dp[k] = dp[k-1] + 2 * dp[k-2]

if n <= 2:
    print(dp[n])
else:
    for i in range(3, n+1):
        dp.append(dp[i-1] + 2 * dp[i-2])
    print(dp[n] % 10007)

