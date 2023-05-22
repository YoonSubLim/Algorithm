
# 230522

from sys import stdin

t = int(stdin.readline().strip())

for _ in range(t):
    words = stdin.readline().split()
    for ch in words[1]:
        print(ch*int(words[0]), end="")
    print()
