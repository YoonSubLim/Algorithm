
# 230619

import sys
input = sys.stdin.readline

for _ in range(int(input())):
    types = {}

    for _ in range(int(input())):
        cloth_name, cloth_type = input().split()
        if cloth_type in types.keys():
            types[cloth_type].append(cloth_name)
        else:
            types[cloth_type] = [cloth_name]
    # print(types)

    answer = 1
    for key in types.keys():
        answer *= len(types[key]) + 1
    answer -= 1 # 모두 안 입는 경우

    print(answer)