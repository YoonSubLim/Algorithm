import re
import sys

# 230906

expression = sys.stdin.readline()

# 정규식 모듈로 + 또는 - 를 기준으로 split
numbers = [int(num) for num in re.split(r'\+|-', expression)]
# 정규식 모듈로 + 또는 - 를 find
operators = re.findall(r'[+-]', expression)

answer = numbers[0]

# - 를 만나면 사실상 그 이후의 모든 숫자를 빼는 것과 동일함
for i in range(1, len(numbers)):
    if operators[i-1] == '-':
        for j in range(i, len(numbers)):
            answer -= numbers[j]
        break
    else:
        answer += numbers[i]

print(answer)

# '-' 를 기준으로 split 하여, 0 번 element - other sum 했으면 더 간단했을 것이다.