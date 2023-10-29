import sys

# 231029

input = sys.stdin.readline


def addNode(num):
    tree.append(num)

    idx = len(tree) - 1
    while idx // 2 >= 1 and tree[idx] >= tree[idx // 2]:
        tree[idx], tree[idx // 2] = tree[idx // 2], tree[idx]
        idx = idx // 2


def deleteMax():
    tree[1], tree[-1] = tree[-1], tree[1]
    print(tree.pop())

    idx = 1
    while True:
        nextIdx = idx

        if idx * 2 <= len(tree) - 1 and tree[idx] <= tree[idx * 2]:
            nextIdx = idx * 2

        if idx * 2 + 1 <= len(tree) - 1 and tree[idx] <= tree[idx * 2 + 1] and tree[idx * 2] <= tree[idx * 2 + 1]:
            nextIdx = idx * 2 + 1

        if nextIdx == idx:
            break
        else:
            tree[idx], tree[nextIdx] = tree[nextIdx], tree[idx]
            idx = nextIdx


tree = [-1]

N = int(input())

for _ in range(N):
    X = int(input())

    if X != 0:
        addNode(X)
        continue

    if len(tree) < 2:
        print(0)
        continue

    deleteMax()
