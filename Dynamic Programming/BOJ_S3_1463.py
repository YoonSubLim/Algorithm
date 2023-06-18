
# 230617

N = int(input())

li = [-1, 0] # i번째 수의 연산 횟수

for i in range(2, N+1):
    if i % 3 == 0 and i % 2 == 0:
        li.append(min(li[i // 3], li[i // 2], li[i-1]) + 1)
    elif i % 3 == 0:
        li.append(min(li[i // 3], li[i-1]) + 1)
    elif i % 2 == 0:
        li.append(min(li[i // 2], li[i-1]) + 1)
    else:
        li.append(li[i-1] + 1)

print(li[N])