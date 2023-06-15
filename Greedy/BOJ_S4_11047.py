
# 230615

import sys
input = sys.stdin.readline

N, K = map(int, input().split())

coins = []

start_coin_idx = 0

for i in range(N):
    coin = int(input())
    coins.append(coin)
    if K >= coin:
        start_coin_idx = i

answer = 0
while True:
    start_coin = coins[start_coin_idx]

    num = K // start_coin
    K -= start_coin * num

    answer += num
    if K == 0:
        break

    start_coin_idx -= 1

print(answer)
