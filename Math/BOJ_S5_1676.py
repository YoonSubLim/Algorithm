
# 230607

import math

answer = 0
for ch in str(math.factorial(int(input())))[::-1]:
    if ch == '0':
        answer += 1
    else:
        break
print(answer)