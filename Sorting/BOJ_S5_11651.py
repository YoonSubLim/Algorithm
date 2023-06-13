# 230613

import sys

input = sys.stdin.readline

li = []

for _ in range(int(input())):
    li.append(tuple(map(int, input().split())))

li.sort(key=lambda tp: (tp[1], tp[0]))

for tp in li:
    print(tp[0], tp[1])