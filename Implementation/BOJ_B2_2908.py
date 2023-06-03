import sys

# 230603

input = lambda:sys.stdin.readline().rstrip()

# A, B = map(list, input().split())
#
# for i in range(1, 4):
#     if A[-i] > B[-i]:
#         print(''.join(reversed(A)))
#         break
#     elif A[-i] < B[-i]:
#         print(''.join(reversed(B)))
#         break

# 문자열 슬라이싱은 문자열의 부분 문자열을 추출하는 기능을 제공하는데, [시작:끝:간격]의 형식으로 사용됩니다.
# 여기서 시작과 끝은 옵셔널하며, 간격은 슬라이스할 요소 간의 간격을 나타냅니다.
A, B = input().split()
A = A[::-1]
B = B[::-1] # reverse

if A > B:
    print(A)
else:
    print(B)