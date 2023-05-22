
# 230522

# 벌집의 m번 째 층의 방 수는 6(m-1) (m>1 일때, m=1 일 때는 1)이다.
# 따라서 방 수는 1 -> 6 -> 12 -> 24 패턴이다.
# m번 째 층의 방까지 도달하기 위해 지나야 하는 최소 방 수는 m 이다. (시작 방 포함)
# 주어진 방 번호 n이 몇번 째 층에 있는지만 알면 된다.

n = int(input())

layer = 2
num = 1
while True:
    if num >= n:
        layer -= 1
        break
    num += 6*(layer-1)
    layer += 1

print(layer)

# 계차수열로 풀어도 된다.
# https://www.acmicpc.net/board/view/111470
# https://terms.naver.com/entry.naver?docId=3350261&cid=60210&categoryId=60210