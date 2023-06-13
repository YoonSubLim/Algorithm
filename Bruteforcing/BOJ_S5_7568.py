
# 230614

import sys
input = sys.stdin.readline

tuple_li = []

for _ in range(int(input())):
    person = tuple(map(int, input().split()))
    tuple_li.append(person)

for i in range(len(tuple_li)):
    person = tuple_li[i]
    count = 0
    for tp in tuple_li:
        if person[0] < tp[0] and person[1] < tp[1]:
            count += 1
    print(count + 1, end=' ')
