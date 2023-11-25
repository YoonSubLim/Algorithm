# 231125

import math

for _ in range(int(input())):
    N, M = map(int, input().split())
    print(math.factorial(M) // (math.factorial(M-N) * math.factorial(N)))
