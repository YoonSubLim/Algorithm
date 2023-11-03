
# 231102

area = [[False] * 100 for _ in range(100)]

for _ in range(int(input())):
    X, Y = map(int, input().split())

    for i in range(10):
        for j in range(10):
            area[X+i][Y+j] = True

result = 0

for i in range(100):
    for j in range(100):
        if area[i][j]:
            result += 1

print(result)

