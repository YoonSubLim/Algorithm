# 230529

x, y = map(int, input().split())

# y/x -> z %
# y+?/x+? -> z+1 %
# ? ->

z = y * 100 // x

if z >= 99:
    print(-1)
else:
    answer = 0
    while True:
        answer += 1000
        if int((y + answer) * 100 / (x + answer)) >= z + 1:
            answer -= 1000
            break

    while True:
        answer += 1
        if int((y + answer) * 100 / (x + answer)) >= z + 1:
            print(answer)
            break

