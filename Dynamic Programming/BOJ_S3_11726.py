
# 230620

## 기존 풀이

# import math
#
# n = int(input())
# answer = 0
#
# width1 = n  # 세로 개수
# width2 = 0  # 가로 개수
#
# while width1 >= 0:
#     # 슬래시 하나인 나누기 연산은 결과가 무조건 float로 나오는 연산이고, 수가 커지면 그 결과가 아무리 실제로는 정수라고 하더라도 오차가 발생하게 됩니다. 오차 없이 정수형으로 나오게 하려면 // 을 써야 합니다.
#     answer += math.factorial(width1 + width2) // (math.factorial(width1) * math.factorial(width2))
#     width1 -= 2
#     width2 += 1
#
# print(answer % 10007)
#
# # https://www.acmicpc.net/source/62318019


# 230621

## Dynamic Programming

# 피보나치 수열인 이유
# https://www.acmicpc.net/board/view/71651

dp = [-1]
dp.append(1)
dp.append(2)

n = int(input())

if n > 2:
    for i in range(3, n+1):
        dp.append(dp[i-2] + dp[i-1])
print(dp[n] % 10007)
