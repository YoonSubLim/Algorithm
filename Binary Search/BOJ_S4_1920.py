
# 230524

n = input()
n_list = [int(i) for i in input().split()]
n_list.sort()

m = input()
m_list = [int(i) for i in input().split()]


def findNum(startIdx: int, endIdx: int, toFindNum: int):

    if n_list[startIdx] > toFindNum or n_list[endIdx] < toFindNum:
        return 0

    # Case : myList[startIdx] <= toFindNum <= myList[endIdx]
    middleIdx = (startIdx + endIdx) // 2

    if n_list[middleIdx] < toFindNum:
        return findNum(middleIdx+1, endIdx, toFindNum)
    elif n_list[middleIdx] > toFindNum:
        return findNum(startIdx, middleIdx-1, toFindNum)
    else:
        return 1


for num in m_list:
    print(findNum(0, len(n_list)-1, num))

