# 231124

N = int(input())
M = int(input())
S = input()

findS = "IO" * N + "I"
lenOfFindS = len(findS)

li1 = [value for value in S.split("OO") if value != ""]
li2 = []

# print("li1 : ", li1)
for block in li1:
    find_idx = -1
    startIdx = 0
    while True:
        find_idx = block.find("II", startIdx)

        if find_idx == -1:
            remainS = block[startIdx:]
            if len(remainS) >= lenOfFindS:
                li2.append(remainS)
            break

        splitedS = block[startIdx:find_idx+1]
        if len(splitedS) >= lenOfFindS:
            li2.append(splitedS)

        startIdx = find_idx + 1

result = 0
# print("li2 : ", li2)

for block in li2:
    idx = 0
    while idx < len(block):
        find_idx = block.find(findS, idx)
        if find_idx != -1:
            result += 1
            idx = find_idx + 2
        else:
            break

print(result)


"""
count()는 정수를 돌려준다. 예를 들어 fruit = "banana"라면 fruit.count("an")는 2로 계산된다.
count()에서 알아둬야 할 중요한 점이 하나 있다. count()는 부분 문자열이 서로 겹치는 경우를 처리하지 못한다.
예를 들어 fruit.count("ana")의 결과는 1이다. "ana"가 두 번 들어 있기는 하지만 "a"가 중간에 겹치기 때문이다.
"""