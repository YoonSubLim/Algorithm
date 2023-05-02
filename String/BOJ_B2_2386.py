from sys import stdin

while True:
    word = stdin.readline().rstrip()
    if word == '#':
        break
    char = word[0]
    word = word.lower()
    print(char, (word.count(char) - 1))
