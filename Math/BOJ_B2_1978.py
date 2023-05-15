import sys

n = sys.stdin.readline()
nums = list(map(int, sys.stdin.readline().split()))

import math

count = 0

for num in nums:
    if num == 1:
        continue

    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            count -= 1
            break
    count += 1

print(count)
