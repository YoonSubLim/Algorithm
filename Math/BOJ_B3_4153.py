
# 230530

while True:
    word = sorted(map(int, input().split()))
    if word[0] == 0:
        break

    if word[0]**2 + word[1]**2 == word[2]**2:
        print("right")
    else:
        print("wrong")
