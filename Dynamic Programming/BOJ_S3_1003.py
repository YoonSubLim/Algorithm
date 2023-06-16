
# 230616

# 0과 1이 몇번 나오는지 튜플로 저장하고 있는다. (Dynamic Programming)

cases = [(1, 0), (0, 1)] # 0이 1번 / 1이 1번 나온다는 의미


def fibonacci(n):
    if len(cases)-1 >= n:
        return cases[n]

    fibo1 = fibonacci(n-2)
    fibo2 = fibonacci(n-1)
    result = (fibo1[0] + fibo2[0], fibo1[1] + fibo2[1])

    cases.append(result)
    return result


for _ in range(int(input())):
    answer = fibonacci(int(input()))
    print(answer[0], answer[1])
