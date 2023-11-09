
# 231109

for i in range(10):

    result = -1
    dump = int(input())
    heights = list(map(int, input().split()))

    heights.sort(reverse=True)

    while True:
        if dump == 0 or heights[0] - heights[-1] <= 1:
            result = heights[0] - heights[-1]
            break

        heights[0] -= 1
        heights[-1] += 1
        heights.sort(reverse=True)
        dump -= 1

    print(f"#{i+1} {result}")