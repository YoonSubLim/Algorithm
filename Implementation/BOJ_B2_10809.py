
# 230528

li = [-1] * 26

word = input()

for i in range(len(word)):
    if li[ord(word[i])-ord('a')] == -1:
        li[ord(word[i])-ord('a')] = i

print(' '.join(map(str, li)))