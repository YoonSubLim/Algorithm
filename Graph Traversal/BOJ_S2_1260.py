import sys
from collections import deque

# 231020

input = sys.stdin.readline


def dfs(deq, visited, connected_mat):
    if len(deq) == 0:
        return

    v = deq.pop()
    print(v, end=" ")

    for i in range(N):
        if connected_mat[v-1][i] and not visited[i]:
            deq.append(i + 1)
            visited[i] = True
            dfs(deq, visited, connected_mat)


def bfs(deq, visited):
    while deq:
        v = deq.popleft()
        print(v, end=" ")

        for i in range(N):
            if connected_mat[v-1][i] and not visited[i]:
                deq.append(i + 1)
                visited[i] = True


N, M, V = map(int, input().split())

connected_mat = [[False for _ in range(N)] for _ in range(N)]

for _ in range(M):
    a, b = map(int, input().split())
    connected_mat[a - 1][b - 1] = connected_mat[b - 1][a - 1] = True

# DFS
deq = deque()
deq.append(V)

visited_dfs = [False] * N
visited_dfs[V-1] = True

visited_bfs = visited_dfs.copy()

dfs(deq, visited_dfs, connected_mat)
print()

# BFS
deq = deque()
deq.append(V)
bfs(deq, visited_bfs)
