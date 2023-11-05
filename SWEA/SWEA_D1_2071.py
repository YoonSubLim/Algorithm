# https://swexpertacademy.com/main/code/problem/problemDetail.do
# 231105

for i in range(int(input())):
    total = sum(list(map(int, input().split())))
    avg = round(total / 10, 0)
    print(f"#{i+1} {int(avg)}")
