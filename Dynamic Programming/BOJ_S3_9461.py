
# 230620

p = [0, 1, 1, 1, 2, 2, 3, 4, 5, 7, 9]

for i in range(11, 101):
    # i 번째 값 추가
    p.append(p[i-1] + p[i-5])

for _ in range(int(input())):
    case = int(input())
    print(p[case])
