
num_input = input()
num_cnt = [0] * 10

for num in num_input:
    num_cnt[int(num)] += 1

sum = num_cnt[6] + num_cnt[9]
num_cnt[6] = sum//2
num_cnt[9] = sum-sum//2

max = num_cnt[0]
for i in range(len(num_cnt)):
    if max < num_cnt[i]:
        max = num_cnt[i]

print(max)