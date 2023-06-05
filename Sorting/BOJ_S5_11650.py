import sys

# 230605

input = sys.stdin.readline

answer = []

for _ in range(int(input())):
    answer.append(tuple(map(int, input().split())))

answer.sort(key=lambda tp: (tp[0], tp[1]))
for tp in answer:
    print(tp[0], tp[1])

