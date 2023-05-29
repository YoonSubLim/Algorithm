import sys
# 230529

from collections import Counter


def find_mode(nums):
    counts = Counter(nums)  # Counter({1: 1, 3: 1, 8: 1, -2: 1, 2: 1}) -> 리스트 요소 개수 세줌
    # print(counts.values()) # count 수
    # print(counts.keys()) # 리스트 요소
    # print(counts.items()) # key-value 쌍

    max_count = max(counts.values())
    # for num, count in counts.items()는 counts 딕셔너리의 각 키-값 쌍을 순회하면서 num에는 키(key)를, count에는 값(value)을 할당합니다.
    modes = [num for num, count in counts.items() if count == max_count]

    if len(modes) == 1:  # 여러 개면 두번째
        return modes[0]
    else:
        return sorted(modes)[1]


li = []
for _ in range(int(sys.stdin.readline())):
    li.append(int(sys.stdin.readline()))

print(round(sum(li) / len(li)))  # 평균
print(sorted(li)[len(li) // 2])  # 중앙값
print(find_mode(li))  # 최빈값
print(max(li) - min(li))  # 범위
