
# 230902

N = int(input())

dp = [4] * (N + 1)

k = 1
while k**2 <= N:
    dp[k**2] = 1
    k += 1

for i in range(1, N+1):
    if dp[i] == 1:
        continue

    j = 1
    while j**2 < i:
        dp[i] = min(dp[i], dp[i-j**2] + 1) # dp[j**2] == 1
        if dp[i] == 2: break
        j += 1

print(dp[N])