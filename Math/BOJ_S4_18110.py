
# 230613

import sys
from _decimal import Decimal, ROUND_HALF_UP

input = sys.stdin.readline

n = int(input())

if n == 0:
    print(0)
else:
    # float에 대한 round() 의 동작은 예상과 다를 수 있습니다
    # 3.5 / 4.5 -> 모두 4 로 반올림 된다. 아래 위가 같은 차이일 경우 짝수 쪽으로 반올림된다.

    # Decimal 객체에 문자열(더 정확하다 함)로 반올림할 숫자를 전달하고,
    # quantize 로 반올림, Deciaml('0') 은 반올림할 자리, rounding 정책은 ROUND_HALF_UP (일반적인 반올림)
    # Deciaml('0.x') 식이면 소수점 1번째 자리까지
    cut = Decimal(str(n * 0.15)).quantize(Decimal('0'), rounding=ROUND_HALF_UP)
    print(cut)
    difficulty = [0] * 31

    for _ in range(n):
        difficulty[int(input())] += 1

    # cut bottom
    tmp = cut
    idx = 0
    while tmp != 0:
        if difficulty[idx] > 0:
            difficulty[idx] -= 1
            tmp -= 1
        else:
            idx += 1

    # cut top
    tmp = cut
    idx = len(difficulty)-1
    while tmp != 0:
        if difficulty[idx] > 0:
            difficulty[idx] -= 1
            tmp -= 1
        else:
            idx -= 1

    total = 0
    for num in range(len(difficulty)):
        if difficulty[num] > 0:
            total += difficulty[num] * num

    print(Decimal(str(total / (n-cut*2))).quantize(Decimal('0'), rounding=ROUND_HALF_UP))
