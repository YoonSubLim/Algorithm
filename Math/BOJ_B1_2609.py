# 230604

a, b = map(int, input().split())


# 유클리드 호제법
# 서로 나누며 남은 나머지를 새롭게 나누는 수로 하되, (a)
# 나머지가 0일 때의 나누는 수(제수) (B) 가 GCD 가 된다.
def euclidean(A, B):
    a = A % B
    if a == 0:
        return B
    return euclidean(B, a)


GCD = euclidean(a, b)  # Greatest Common Divisor
LCM = int((a * b) / GCD)  # Least Common Multiple (두 수의 곱을 GCD 로 나눈 값)

print(GCD)
print(LCM)
