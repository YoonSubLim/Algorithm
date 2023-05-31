
# 230531

import sys
input = sys.stdin.readline

li = []

for _ in range(int(input())):
    li.append(int(input()))

for i in sorted(li):
    print(i)
