import sys

# 231016

# X1 X2 X3 .... Xn 이 중복되지 않은, 정렬된 수라고 가정했을 때,
# X1 보다 작은 수의 개수 f(X1) = 0
# X2 보다 작은 수의 개수 f(X2) = f(X1) + 1 = 1
# X3 보다 작은 수의 개수 f(X3) = f(X2) + 1 = 2
# X4 보다 작은 수의 개수 f(X4) = f(X3) + 1 = 3
# Xn 보다 작은 수의 개수 f(Xn) = f(Xn-1) + 1 = n-1

_ = sys.stdin.readline()

nums = list(map(int, sys.stdin.readline().split()))

# 중복되지 않은, 정렬된 수
distinct_nums = list(set(nums))
distinct_nums.sort()

# key 보다 작은 수의 개수 value 를 key-value 로 저장
num_count = dict()

for i in range(len(distinct_nums)):
    num_count[distinct_nums[i]] = i

for num in nums:
    print(num_count[num], end=" ")
