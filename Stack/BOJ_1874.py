import sys

# 230427

# push and pop
# pop 을 이미 했는데, pop 하려할 때 NO

n = int(sys.stdin.readline())

stck = []

i = 0 # i 값까지 들어갔음을 의미함
printStr = ''

for _ in range(n):
    num = int(sys.stdin.readline())

    # push
    while i < num:
        # num 까지 append
        i += 1
        stck.append(i)
        printStr += '+\n'

    # push 를 했든, 안했든. 스택 최상단의 값이 빼야할 값이면 pop
    if stck[-1] == num:
        stck.pop()
        printStr += '-\n'
    # 스택 최상단의 값(stck[-1]) 이 빼야할 값 (num) 이 아니라면, pop 할 수 없고 종료.
    else:
        printStr = 'NO'
        break

print(printStr)