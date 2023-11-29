# 231129

"""
    # a % M = x / a % N = y 을 찾으려 했으나, x = M / y = N 인 경우 때문에 아래와 같이 수정
    (a - x) % M = 0
    (a - y) % N = 0

    a = Mk + x 꼴 (k는 0 이상의 정수) 이면서,
    a = Nk' + y 꼴 (k'는 0 이상의 정수) 이다.

    x / M+x / 2M+x / 3M+x / ....
    y / N+y / 2N+y / 3N+y / ....
    에서 가장 처음으로 겹치는 수(k != k' 일 수 있다.)가 a 가 되고,
    M*N 을 넘어가면 -1을 출력한다.
"""

for _ in range(int(input())):
    M, N, x, y = map(int, input().split())

    a = x  # x 로 시작하든 y 로 시작하든 상관없다.
    while True:
        if (a - x) % M == 0 and (a - y) % N == 0:
            print(a)
            break
        elif a > M * N:
            print(-1)
            break
        a += M
