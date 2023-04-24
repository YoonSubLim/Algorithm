input = input()
list = [0 for i in range(26)]

for ch in input:
    list[ord(ch)-ord('a')] += 1

print(' '.join(map(str, list)))
