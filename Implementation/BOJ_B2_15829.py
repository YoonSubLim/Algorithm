
# 230613

n = int(input())
wd = input()

sum = 0
for i in range(n):
    num = ord(wd[i])-ord('a')+1
    sum += num * (31 ** i)
print(sum % 1234567891)
