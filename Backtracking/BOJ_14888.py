import sys

n = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().split()))
add, sub, mul, div = map(int, sys.stdin.readline().split())

max_val = -1e9  # -10억
min_val = 1e9


def calculate(current, next_idx):
    global add, sub, mul, div, max_val, min_val

    # 끝까지 연산이 끝났을 때 값 비교
    if next_idx == n:  # 배열에서의 n은 초과범위. 즉, 이때의 current 값이 최종 결과값이고, max or min 값 대치 후 빠져나간다.
        max_val = max(max_val, current)
        min_val = min(min_val, current)
        return

    # add 부터 먼저 사용하는 케이스를 모두 탐색한 후,
    if add > 0:  # + 남아있으면
        add -= 1
        calculate(current + nums[next_idx], next_idx + 1)  # 더해주고, 다음 연산자로 넘긴다.
        # 빠져 나왔을 경우에는, 다른 케이스를 대비해서 뺐던 연산자를 다시 더해준다.
        add += 1

    # sub 부터 먼저 사용하는 케이스를 모두 탐색
    if sub > 0:
        sub -= 1
        calculate(current - nums[next_idx], next_idx + 1)  # 빼주고, 다음 연산자로 넘긴다.
        sub += 1

    # mul 부터 먼저 사용하는 케이스를 모두 탐색
    if mul > 0:
        mul -= 1
        calculate(current * nums[next_idx], next_idx + 1)  # 곱해주고, 다음 연산자로 넘긴다.
        mul += 1

    # div 부터 먼저 사용하는 케이스를 모두 탐색
    if div > 0:
        div -= 1
        calculate(int(current / nums[next_idx]), next_idx + 1)  # 나눠주고, 다음 연산자로 넘긴다.
        div += 1


calculate(nums[0], 1)
print(max_val)
print(min_val)
