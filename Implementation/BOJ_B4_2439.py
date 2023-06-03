
# 230603

N = input()
form = "%" + N + "s"

for i in range(1, int(N)+1):
    print(form % ('*' * i))


# 서식 지정자

# print("이름: %s, 나이: %f" % (name, age)) -> 튜플 형태
# # 출력: 이름: Alice, 나이: 25.000000
#
# print("이름: %(name)s, 나이: %(age)d" % {"name": name, "age": age}) -> 딕셔너리 형태
# # 출력: 이름: Alice, 나이: 25

# %5s -> 문자열 길이 5 & 오른쪽 정렬
# %-5s -> 문자열 길이 5 & 왼쪽 정렬
# %.2f -> 소수점 2자리