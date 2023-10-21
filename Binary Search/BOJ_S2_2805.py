import sys

# 231021

N, M = map(int, sys.stdin.readline().split())

heights = list(map(int, sys.stdin.readline().split()))
start, end = 1, max(heights)

while start <= end:
    middle = (start + end) // 2

    cut_sum = sum(height - middle for height in heights if height >= middle)

    if cut_sum < M:
        end = middle - 1
        continue

    if cut_sum >= M:
        start = middle + 1
        continue

print(end)
