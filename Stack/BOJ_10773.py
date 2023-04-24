import sys

n = int(sys.stdin.readline())
stck = []

for _ in range(n):
    num = int(sys.stdin.readline())
    if num == 0:
        stck.pop()
    else:
        stck.append(num)

sum = 0
for i in stck:
    sum += i

print(sum)