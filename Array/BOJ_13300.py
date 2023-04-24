
man = [0, 0, 0, 0, 0, 0]
woman = [0, 0, 0, 0, 0, 0]

word = input()
N = int(word.split()[0])
K = int(word.split()[1])

word = list()
for i in range(N):
    word.append(input())

for i in range(N):
    sex = int(word[i].split()[0])
    grade = int(word[i].split()[1])
    if sex == 0:
        woman[grade-1] += 1
    else:
        man[grade-1] += 1

count = 0
for i in range(6):
    count += man[i]//K
    count += woman[i]//K
    if man[i] % K != 0:
        count += 1
    if woman[i] % K != 0:
        count += 1

print(count)