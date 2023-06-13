
# 230613

# int : 4 byte
# 1 MB : 약 1,000,000 byte / 2^20 byte / 10^6 byte
# 10,000,000 개의 int 배열을 만든다면
# 40,000,000 byte -> 40 MB 의 메모리를 차지할 것이다.

# 파이썬은 1초에 대략 2000만번의 연산이 가능하다고 전제하면 안전하다.
# 10,000,000 개의 데이터를 1초에 O(N) 으로도 처리할 수 있다.

# 이 문제는 시간보다 메모리에 중점을 두어 해결해야 한다.

import sys
input = sys.stdin.readline

count_num = [0] * 10001

for _ in range(int(input())):
    num = int(input())
    count_num[num] += 1

for i in range(len(count_num)):
    while count_num[i] != 0:
        print(i)
        count_num[i] -= 1
