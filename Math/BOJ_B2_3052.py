
# 230527

li = []

for _ in range(10):
    li.append(int(input()) % 42)

print(len(list(set(li))))
