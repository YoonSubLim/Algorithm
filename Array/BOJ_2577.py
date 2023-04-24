
# 8분

mul = 1

for i in range(3):
    mul *= int(input())

num = [0,0,0,0,0,0,0,0,0,0]

for i in str(mul):
    num[int(i)] += 1

for i in num:
    print(i)