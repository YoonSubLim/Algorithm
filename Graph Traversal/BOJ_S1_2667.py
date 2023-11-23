from collections import deque

# 231123

N = int(input())
mat = []
directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]
counts = []

for _ in range(N):
    mat.append([int(ch) for ch in input()])

for i in range(N):
    while 1 in mat[i]:
        deq = deque()
        count = 1

        j = mat[i].index(1)
        deq.append((i, j))
        mat[i][j] = 0

        while deq:
            x, y = deq.popleft()

            for direct in directions:
                newX = x + direct[0]
                newY = y + direct[1]
                if 0 <= newX < N and 0 <= newY < N and mat[newX][newY] == 1:
                    deq.append((newX, newY))
                    mat[newX][newY] = 0
                    count += 1

        counts.append(count)

print(len(counts))
for count in sorted(counts):
    print(count)

"""
직전 풀이에서는
7
0110101
0110100
1110101
0000111
0100000
0111110
0111000
의 mat[0][6] 과 같이,
한 라인에서 추가적으로 count 될 수 있는 단지가 누락되었다.
"""

