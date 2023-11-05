# https://swexpertacademy.com/main/code/problem/problemDetail.do
# 231105

for i in range(int(input())):
    nums = list(map(int, input().split()))
    x = abs(nums[1] - ((nums[2] + nums[0]) / 2))

    print(f"#{i + 1} {round(x, 1)}")
