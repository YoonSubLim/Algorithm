
# 230524

import math

m, n = map(int, input().split())


def isPrime(num: int):
    if num < 2:
        return

    for i in range(2, int(math.sqrt(num))+1):
        if num % i == 0:
            return

    print(num)


for i in range(m, n+1):
    isPrime(i)