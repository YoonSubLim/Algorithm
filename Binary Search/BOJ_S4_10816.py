
# 230604

_ = input()
n_li = list(map(int, input().split()))
_ = input()
m_li = list(map(int, input().split()))

n_li.sort()


def findStart(li, target, start, end):
    if start > end:
        return -1
    middle = (start + end) // 2

    if li[middle] < target:
        return findStart(li, target, middle + 1, end)
    elif li[middle] > target:
        return findStart(li, target, start, middle - 1)
    else:
        if middle == 0:
            return 0
        elif li[middle - 1] == target:
            return findStart(li, target, start, middle - 1)
        elif li[middle - 1] != target:
            return middle


def findEnd(li, target, start, end):
    if start > end:
        return -1
    middle = (start + end) // 2

    if li[middle] < target:
        return findEnd(li, target, middle + 1, end)
    elif li[middle] > target:
        return findEnd(li, target, start, middle - 1)
    else:
        if middle == len(n_li)-1:
            return len(n_li)-1
        elif li[middle + 1] == target:
            return findEnd(li, target, middle + 1, end)
        elif li[middle + 1] != target:
            return middle


for target_num in m_li:
    start_idx = findStart(n_li, target_num, 0, len(n_li)-1)
    end_idx = findEnd(n_li, target_num, 0, len(n_li)-1)
    if start_idx == -1:
        print(0, end=" ")
    else:
        print(end_idx - start_idx + 1, end=" ")
