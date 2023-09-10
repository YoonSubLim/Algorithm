import sys

# 230910

input = sys.stdin.readline
heapArr = []


def insertHeap(arr, num):
    arr.append(num)
    idx = len(arr) - 1

    while idx != 0 and arr[(idx - 1) // 2] > arr[idx]:  # idx-1 // 2 : parent's idx
        arr[(idx - 1) // 2], arr[idx] = arr[idx], arr[(idx - 1) // 2]  # swap
        idx = (idx - 1) // 2


def deleteHeap(arr):
    print(arr[0])
    arr[0] = arr[-1]
    arr.pop()

    idx = 0
    while True:
        # leftChild
        if (idx * 2) + 1 > len(arr) - 1:
            break

        nextMinValue = arr[(idx * 2) + 1]
        nextIdx = (idx * 2) + 1

        # rightChild
        if (idx * 2) + 2 <= len(arr) - 1:
            if nextMinValue > arr[(idx * 2) + 2]:
                nextMinValue = arr[(idx * 2) + 2]
                nextIdx = (idx * 2) + 2

        # Child vs Parent
        if arr[idx] > nextMinValue:
            arr[idx], arr[nextIdx] = arr[nextIdx], arr[idx]
            idx = nextIdx
        else:
            break


for _ in range(int(input())):
    num = int(input())
    if num == 0:
        if len(heapArr) == 0:
            print(0)
            continue
        deleteHeap(heapArr)
    else:
        insertHeap(heapArr, num)
