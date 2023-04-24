
count = int(input())

nums = input()
nums = nums.split()

find = input()

count = 0
for num in nums:
    if int(num) == int(find):
        count += 1

print(count)