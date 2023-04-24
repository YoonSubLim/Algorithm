import sys

n = sys.stdin.readline()

for _ in range(int(n)):
    vps = sys.stdin.readline().strip()
    stck = []
    flag = True

    for ch in vps:
        if ch == '(':
            stck.append(ch)
        elif len(stck) == 0:
            flag = False
            break
        else:
            stck.pop()

    if len(stck) != 0:
        flag = False

    if flag == True:
        print("YES")
    else:
        print("NO")

