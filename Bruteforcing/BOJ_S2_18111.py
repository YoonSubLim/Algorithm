
# 230614

import sys
input = sys.stdin.readline

N, M, B = map(int, input().split())
blocks = []

for _ in range(N):
    blocks.append(list(map(int, input().split())))

answer = (500*500*256*2, -1)

for height in range(257):
    isrt_block = 0
    del_block = 0
    for row in blocks:
        for num in row:
            # 채워야 할 블록 수와 소요 시간
            if height > num:
                isrt_block += height - num
            # 걷어야 할 블록 수와 소요 시간
            elif height < num:
                del_block += num - height

    # 빼고 채우는 작업이 인벤토리 블럭 수로 가능하다면
    if isrt_block-del_block <= B:
        need_time = isrt_block + del_block * 2
        # 소요 시간 더 작으면 교체 # 작은 높이부터 시작하므로 마지막은 최대 높이로 설정됨
        if need_time <= answer[0]:
            answer = (need_time, height)

print(answer[0], answer[1])

