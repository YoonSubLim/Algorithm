
# 230618

import sys
input = sys.stdin.readline

li = []

for _ in range(int(input())):
    li.append(tuple(map(int, input().split())))

li.sort(key=lambda tp: (tp[1], tp[0]))

time_block = (-1, -1)
answer = 0
for tp in li:
    if tp[0] >= time_block[1]:
        answer += 1
        time_block = tp

print(answer)
