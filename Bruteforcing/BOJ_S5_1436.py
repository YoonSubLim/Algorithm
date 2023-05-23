
# 230523

n = int(input())
num = 666

while True:
    if str(num).count('666') >= 1:
        n -= 1
    if n == 0:
        break
    num += 1
print(num)

# if '666' in str(num):
#     n -= 1
# 로 해도 된다.