
# 230525

for _ in range(int(input())):
    word = input()

    total = 0
    sum = 0

    for i in word:
        if i == 'O':
            sum += 1
            total += sum
        else:
            sum = 0

    print(total)