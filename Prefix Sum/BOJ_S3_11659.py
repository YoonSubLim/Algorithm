
# 230620

import sys
input = sys.stdin.readline

N, M = map(int, input().split())
N_li = list(map(int, input().split()))

prefix_sum = 0
for i in range(N):
    prefix_sum += N_li[i]
    N_li[i] = prefix_sum

for _ in range(M):
    i, j = map(int, input().split())
    answer = N_li[j-1]
    if i != 1:
        answer -= N_li[i-2]
    print(answer)
