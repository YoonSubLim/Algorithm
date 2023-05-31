
# 230531

import sys
input = sys.stdin.readline

N, M = map(int, input().split())

nums = list(map(int, input().split()))

answer = 0

### 기존 풀이
# for i in range(N-2):
#     a = nums[i]
#     for j in range(i+1, N-1):
#         b = nums[j]
#         for k in range(j+1, N):
#             c = nums[k]
#             if a+b+c <= M:
#                 answer = max(answer, a+b+c)


### combinations 사용
from itertools import combinations

for x in combinations(nums, 3):
    total = sum(x)
    if total <= M:
        answer = max(answer, total)

print(answer)
