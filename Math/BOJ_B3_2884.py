
# 230522

h, m = map(int, input().split())

if m < 45:
    if h < 1:
        h = 23
    else:
        h -= 1
    m = m + 15 # m + 60 - 45
else:
    m -= 45

print(h, m)