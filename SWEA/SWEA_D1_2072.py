# https://swexpertacademy.com/main/code/problem/problemDetail.do
# 231105

for i in range(int(input())):
    nums = list(map(int, input().split()))

    count = 0
    for num in nums:
        if num % 2 == 1:
            count += num

    print(f"#{i+1} {count}")
