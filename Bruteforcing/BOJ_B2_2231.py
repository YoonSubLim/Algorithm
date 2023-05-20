# 230520

# 어떤 값 x 가 있는데, x + (x의 각 자리수 합) = N 을 만족하는 가장 작은 x 찾기

n = int(input())
# x 는 n 보다 작을 수밖에 없고, (x의 각 자리수 합) 은 최대 9 * (x 의 길이) 이다.
# 9 -> 9 + 9 -> 18
# 99 -> 99 + 9*2 -> 117
# 999 -> 999 + 9*3 -> 1026
# 9999 -> 9999 + 9*4 -> 10035
# 1000 -> 1000 + 1 -> 1001
# 따라서 x 는 N 과 같은 길이의 수이거나, N-1 길이의 수이다.

start = 0
if n >= 18:
    start = n-9*len(str(n))

for num in range(start, n + 1):
    answer = num
    digits = [int(j) for j in str(num)]
    for j in digits:
        answer += j
    if answer == n:
        print(num)
        break
    if num == n:
        print(0)
        break
