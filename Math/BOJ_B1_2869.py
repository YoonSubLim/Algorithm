
# 230608

A, B, V = map(int, input().split())

oneDay = A-B

if (V-A) % oneDay == 0:
    print((V-A) // oneDay + 1)
else:
    print((V-A) // oneDay + 2)
