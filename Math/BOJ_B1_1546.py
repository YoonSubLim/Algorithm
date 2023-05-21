
# 230521

n = int(input())

scores = [int(score) for score in input().split()]

sum = 0
m = max(scores)

for score in scores:
    sum += score/m*100
print(sum/n)