
# 231126

for _ in range(int(input())):
    x1, y1, r1, x2, y2, r2 = map(int, input().split())

    # 동심원
    if x1 == x2 and y1 == y2:
        # 접접이 무한대
        if r1 == r2:
            print(-1)
        else:
            print(0)
        continue

    distant = ((x1 - x2) ** 2 + (y1 - y2) ** 2) ** (1/2)

    if distant > r1 + r2:
        print(0)
    elif distant == r1 + r2:
        print(1)
    else:
        if abs(r1 - r2) == distant:
            print(1)
        elif abs(r1 - r2) > distant:
            print(0)
        # r-r' < d < r+r'
        else:
            print(2)
