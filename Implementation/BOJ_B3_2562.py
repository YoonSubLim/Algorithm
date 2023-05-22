
# 230522

max = -1
idx = -1
for i in range(9):
    wd = int(input())
    if max < wd:
        max = wd
        idx = i
print(max)
print(idx+1)