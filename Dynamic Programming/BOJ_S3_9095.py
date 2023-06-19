
# 230619

import math
import sys
input = sys.stdin.readline

## Math Algorithm
## : 1 / 2 / 3 가능한 개수를 div1 / div2 / div3 로 구분하며, 전체 개수 (div_sum!) / (div1! * div2! * div3!) combination

# for _ in range(int(input())):
#     case = int(input())
#
#     answer = 0
#
#     div3 = case // 3
#     for i in range(div3, -1, -1):
#         div2 = (case - (3 * i)) // 2
#         for j in range(div2, -1, -1):
#             div1 = (case - (3 * i) - (2 * j))
#             div_sum = div1 + i + j
#             answer += math.factorial(div_sum) / (math.factorial(div1) * math.factorial(i) * math.factorial(j))
#
#     print(int(answer))


## Dynamic Programming
dp = [1, 2, 4]

# dp[i-3] + 3 / dp[i-2] + 2 / dp[i-1] + 1 로 dp[i] 에 도달하므로,
# dp[i-3], dp[i-2], dp[i-1] 를 더한 것이 dp[i] 값이 된다.
for i in range(3, 11):
    dp.append(dp[i-3] + dp[i-2] + dp[i-1])

for _ in range(int(input())):
    print(dp[int(input())-1])