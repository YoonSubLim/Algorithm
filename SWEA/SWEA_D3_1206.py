# https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV134DPqAA8CFAYh&categoryId=AV134DPqAA8CFAYh&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=1
# 231105

for i in range(10):
    N = int(input())
    heights = list(map(int, input().split()))
    count = 0

    for j in range(2, N-2):
        aroundMax = max(heights[j-2], heights[j-1], heights[j+2], heights[j+1])
        if heights[j] > aroundMax:
            count += heights[j] - aroundMax

    print(f"#{i+1} {count}")
