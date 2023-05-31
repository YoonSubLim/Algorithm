
# 230524

# import math

### 기존 풀이

# m, n = map(int, input().split())

# def isPrime(num: int):
#     if num < 2:
#         return
#
#     for i in range(2, int(math.sqrt(num))+1):
#         if num % i == 0:
#             return
#
#     print(num)
#
#
# for i in range(m, n+1):
#     isPrime(i)

# 230531

### 에라토스테네스의 체

m, n = map(int, input().split())

sieve = [True] * (n+1)
sieve[0] = sieve[1] = False

end = int(n ** 0.5)
for i in range(2, end+1): # 2 ~ n^(1/2)
    if sieve[i]:
        for j in range(i+i, n+1, i): # startIdx, endIdx(n), step -> i 의 배수는 모두 False 처리
            sieve[j] = False

for i in range(m, n+1):
    if sieve[i]:
        print(i)
