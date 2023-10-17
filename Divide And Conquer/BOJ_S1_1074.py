import sys

# 231017

# 4분면 상에서 어디 위치해있는지 를 판단하여,
# 4분면이라면 3분면까지의 수의 개수를 count 에 더하며
# N이 1이 될 때까지 반복함

N, r, c = map(int, sys.stdin.readline().split())

count = 0

while N != 0:
    biggerHalfRow = False
    biggerHalfCol = False

    if 2 ** (N - 1) <= r:
        biggerHalfRow = True
        r -= 2 ** (N - 1)
    if 2 ** (N - 1) <= c:
        biggerHalfCol = True
        c -= 2 ** (N - 1)

    if biggerHalfRow:
        count += (2 ** (2 * N - 2)) * 2
    if biggerHalfCol:
        count += 2 ** (2 * N - 2)

    N -= 1

print(count)
