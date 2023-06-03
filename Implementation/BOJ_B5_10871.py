
# 230603

N, X = map(int, input().split())

print(' '.join(list(filter(lambda num: int(num) < X, input().split()))))
