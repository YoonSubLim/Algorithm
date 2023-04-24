
n = int(input())
nums = list(map(int, input().split()))
x = int(input())
cnt = 0

start = 0
end = len(nums) - 1
nums.sort()

while nums[end] >= x:
    end -= 1

for i in range(n):
    if start >= end:
        break

    if nums[start] + nums[end] > x:
        end -= 1
    elif nums[start] + nums[end] < x:
        start += 1
    elif nums[start] + nums[end] == x:
        cnt += 1
        start += 1

print(cnt)