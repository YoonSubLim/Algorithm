import sys
input = sys.stdin.readline

# < 동적 프로그래밍 >
# 1. 작은 문제들이 반복된다.
# 2. 같은 문제는 구할때 마다 정답이 같다.
# -> 반복되는 문제는 메모 하여 불필요한 연산을 피한다.

# 위의 경우
# getCapacity(K, 1), (K, 2), ... ,(K, N) 까지 연산을 할 때,
# getCapacity(K-1, 1) 은 N 번, (K-1, 2) 는 N-1 번 ... 식으로 같은 연산을 반복한다.


values = [[]]


def getCapacity(k, n):

    # 호수가 더 큰 케이스 나타나면, 호수 배열 확장
    if len(values[0]) < n:
        for arr in values:
            for _ in range(n-len(arr)):
                arr.append(0)

    # 층수가 더 큰 케이스 나타나면, 층수 배열 확장
    if len(values) < k+1:
        for _ in range((k+1)-len(values)):
            values.append([0]*len(values[0]))

    if values[k][n-1] != 0: # 값이 있으면
        return values[k][n-1]

    # 값이 없으면 연산해서 삽입
    if k == 0:
        # 0층일 때는 n명
        values[k][n - 1] = n
        return n
    else:
        value = 0
        for i in range(1, n+1): # i 호
            value += getCapacity(k-1, i) # 밑 층 값 더해서
        values[k][n-1] = value # 삽입
        return value


for _ in range(int(input())):
    K = int(input())
    N = int(input())

    print(getCapacity(K, N))

    # for arr in reversed(values):
    #     print("[", end=' ')
    #     for num in arr:
    #         print(num, end=' ')
    #     print("]")


