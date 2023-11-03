
# 231103

isSelfNum = [True] * 10000


def checkDn(num):
    number = num

    chs = str(number)
    for ch in chs:
        number += int(ch)

    if number > 10000:
        return

    isSelfNum[number-1] = False

    checkDn(number)


for i in range(10000):
    if isSelfNum[i]:
        checkDn(i+1)


for i in range(10000):
    if isSelfNum[i]:
        print(i+1)
