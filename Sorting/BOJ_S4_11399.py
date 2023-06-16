
_ = input()

p_list = sorted(list(map(int, input().split())))

answer = 0
for i in range(len(p_list)):
    answer += p_list[i] * (len(p_list)-i)

print(answer)