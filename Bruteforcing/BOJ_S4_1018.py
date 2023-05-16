
# 230516

import sys

m, n = map(int, sys.stdin.readline().strip().split())
board = []
for _ in range(m):
    board.append(list(sys.stdin.readline().strip()))

draw_min = 64

for row in range((m-8)+1):
    for col in range((n-8)+1):
        head = board[row][col]
        # head 가 그대로인 경우
        # head 가 잘못된 경우
        # 두 가지 경우 모두 고려
        start_white = sum(
            1 # 더해라
            for r in range(8)
            for c in range(8)
            if (r+c) % 2 == 0 and board[row+r][col+c] != head # 같아야 할 때 같지 않거나
            or (r+c) % 2 == 1 and board[row+r][col+c] == head # 같지 않아야 할 때 같거나
        )
        start_black = sum(
            1  # 더해라
            for r in range(8)
            for c in range(8)
            if (r + c) % 2 == 0 and board[row + r][col + c] == head  # 같아야 할 때 같지 않거나
            or (r + c) % 2 == 1 and board[row + r][col + c] != head  # 같지 않아야 할 때 같거나
        )

        draw_min = min(draw_min, start_white, start_black)

print(draw_min)