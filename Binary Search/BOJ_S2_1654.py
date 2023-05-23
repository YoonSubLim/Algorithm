# 230523

# 종료 조건에서 end 를 반환하는 이유는,
# 1. start 가 cm + 1 했든 -> start <= end 일 때 divided_num >= n 이었던 것 -> 변하지 않은 end 값이 answer
# 2. end 가 cm - 1 했든 -> start <= end 일 때 divided_num < n 이었던 것
# 2 번의 경우 '기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자.' 에 따라 (cm - 1) 한 end 가 답일 수밖에 없게 된다.

# 재귀함수 없이 while 만으로 풀어도 된다.
# https://claude-u.tistory.com/443

# 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자.
def find_answer(start, end):
    # 종료 조건
    if start > end:
        return end

    cm = (start + end) // 2

    # 각 랜선 잘랐을 때, 몇 조각 나오는지
    divided_num = sum(length // cm for length in lengths)

    if divided_num >= n: # 더 길게 잘라도 됨
        return find_answer(cm + 1, end)
    else: # 더 잘게 잘라야 함
        return find_answer(start, cm - 1)


k, n = map(int, input().split())
lengths = [int(input()) for _ in range(k)]
lengths.sort()

print(find_answer(1, lengths[-1]))
