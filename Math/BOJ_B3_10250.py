
# 230604

import sys

input = sys.stdin.readline

for _ in range(int(input())):
    H, W, N = map(int, input().split())
    room_floor = N % H # 몇 층
    room_num = N // H + 1 # 몇 호
    if room_floor == 0:
        room_floor = H
        room_num -= 1

    print("%d%02d" % (room_floor, room_num))