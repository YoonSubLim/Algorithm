
# 230616

import sys
input = sys.stdin.readline

N, M = map(int, input().split())

passwords = dict()

for _ in range(N):
    url, pw = input().split()
    passwords[url] = pw

for _ in range(M):
    print(passwords[input().strip()])