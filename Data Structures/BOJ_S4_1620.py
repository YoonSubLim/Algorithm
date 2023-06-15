
# 230615

import sys
input = sys.stdin.readline

N, M = map(int, input().split())

dogam = dict()
dogam_num = dict()

for i in range(N):
    poketmon = input().rstrip()
    dogam[poketmon] = i+1
    dogam_num[i+1] = poketmon

for _ in range(M):
    wd = input().rstrip()
    # isdigit : 문자열이 숫자로 이루어져 있는지 판단해줌
    if str.isdigit(wd):
        print(dogam_num[int(wd)])
    else:
        print(dogam[wd])