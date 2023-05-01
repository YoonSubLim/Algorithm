from sys import stdin

x, y, w, h = map(int, stdin.readline().split())

print(min(min(w-x, x), min(h-y, y)))