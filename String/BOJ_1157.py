from sys import stdin

## 230501

word = stdin.readline().strip()
chars = [0] * 26

for ch in range(ord('a'), ord('z')+1):
    chars[ch-ord('a')] += word.count(chr(ch))
for ch in range(ord('A'), ord('Z')+1):
    chars[ch-ord('A')] += word.count(chr(ch))

if chars.count(max(chars)) > 1:
    print("?")
else:
    print(chr(chars.index(max(chars)) + ord('A')))
