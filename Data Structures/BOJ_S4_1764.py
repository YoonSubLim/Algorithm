
# 230615

import sys
input = sys.stdin.readline

N, M = map(int, input().split())

a = set()
b = set()

for _ in range(N):
    a.add(input().rstrip())
for _ in range(M):
    b.add(input().rstrip())

a = a.intersection(b)
a = sorted(list(a))

print(len(a))
for person in a:
    print(person)
