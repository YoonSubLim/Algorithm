import sys

# 230911

N = int(sys.stdin.readline())
matrix = []
white = 0
blue = 0

def checkArray(row, col, n):
    global white, blue # white와 blue를 전역 변수로 사용하겠다고 선언

    isAllWhite = True
    isAllBlue = True
    for i in range(row, row + n):
        for j in range(col, col + n):
            if matrix[i][j] == 0:
                isAllBlue = False
            elif matrix[i][j] == 1:
                isAllWhite = False

            if (not isAllWhite) and (not isAllBlue):
                checkArray(row, col, n//2)
                checkArray(row+n//2, col, n//2)
                checkArray(row, col+n//2, n//2)
                checkArray(row+n//2, col+n//2, n//2)
                return

    if isAllWhite:
        white += 1
    elif isAllBlue:
        blue += 1
    else:
        print("Error")


for _ in range(N):
    matrix.append(list(map(int, sys.stdin.readline().split())))

checkArray(0, 0, N)
print(white)
print(blue)

