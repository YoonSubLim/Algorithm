# 2022 KAKAO TECH INTERNSHIP
# https://school.programmers.co.kr/learn/courses/30/lessons/118667
from collections import deque

# 231123-24

# -> 시간초과
# def solution(queue1, queue2):
#     queue3 = queue1 + queue2
#     startIdx1 = 0
#     startIdx2 = len(queue1)
#
#     if sum(queue3) % 2 == 1:
#         return -1
#
#     target = sum(queue3) // 2
#
#     results = []
#
#     for i in range(len(queue3)):
#         for j in range(i, len(queue3)):
#             sumOfA = sum(queue3[i:j])
#             if sumOfA == target:
#                 results.append((i - startIdx1) + (j - startIdx2))
#
#     if len(results) == 0:
#         return -1
#
#     return min(results)

# 길이가 같은 두 개의 큐
def solution(queue1, queue2):
    queue1, queue2 = deque(queue1), deque(queue2)
    sum1, sum2 = sum(queue1), sum(queue2)

    total = sum1 + sum2
    if total % 2 != 0:
        return -1

    target = total // 2
    count = 0

    for _ in range(len(queue1) * 3):
        if sum1 == target:
            return count

        if sum1 > target:
            pop_num = queue1.popleft()
            queue2.append(pop_num)
            sum1 -= pop_num
            sum2 += pop_num
        elif sum2 > target:
            pop_num = queue2.popleft()
            queue1.append(pop_num)
            sum1 += pop_num
            sum2 -= pop_num
        count += 1

    return -1


print(solution([3, 2, 7, 2], [4, 6, 5, 1]))
print(solution([1, 2, 1, 2], [1, 10, 1, 2]))
print(solution([1, 2, 3, 4], [1, 2, 3, 4]))
print(solution([1, 1], [1, 5]))
print(solution([1, 2], [4, 1]))
print(solution([1, 1, 1, 1, 1], [1, 1, 1, 9, 1]))

"""
# 1 1 1 1 1 // 1 1 1 9 1
# 1 1 1 1 1 1 // 1 1 9 1
# 1 1 1 1 1 1 1 // 1 9 1
# 1 1 1 1 1 1 1 1 // 9 1
# 1 1 1 1 1 1 1 1 9 // 1
# 1 1 1 1 1 1 1 9 // 1 1
# 1 1 1 1 1 1 9 // 1 1 1
# 1 1 1 1 1 9 // 1 1 1 1
# 1 1 1 1 9 // 1 1 1 1 1
# 1 1 1 9 // 1 1 1 1 1 1
# 1 1 9 // 1 1 1 1 1 1 1
# 1 9 // 1 1 1 1 1 1 1 1
# 9 // 1 1 1 1 1 1 1 1 1
# answer : 12

위의 예시와 같이 단순 두 큐의 합 (len(queue1) * 2) 이상의 횟수가 나올 수 있다.

최악의 경우,
1, 2, 3, 4 ... N // 1, 2, 3, 4, ... N-1 N
1, 2, 3, 4 ... N 1, 2, 3... N-1(K) // N  # N-1 번
N-1 (K) // N 1, 2, ... N 1, 2, ... N-2 // # N + N-2 번
총 3N-3 번 이라고 판단했고,
3N 번 for 문을 도는 것으로 수정했다.
"""