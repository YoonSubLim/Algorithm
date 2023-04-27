from sys import stdin

n = stdin.readline()
nums = []
for _ in range(int(n)):
    nums.append(int(stdin.readline().strip()))
nums.sort()
for ch in nums:
    print(ch)

### 좋은 코드
# print(*sorted([i for i in map(int, open(0).read().split()[1:])]), sep='\n')

# open(0)은 표준 입력(stdin)을 읽기 위한 파일과 유사한 객체를 엽니다.
# read() 메서드를 사용하여 전체 입력을 문자열로 읽어들입니다.
# split() 메서드를 사용하여 문자열을 공백으로 분리하여 각 요소를 리스트로 만듭니다.
# 리스트 슬라이싱을 사용하여 n을 제외한 나머지 정수 요소를 선택합니다.
# map() 함수를 사용하여 각 요소를 int로 변환합니다.
# 리스트 컴프리헨션을 사용하여 변환된 정수 요소를 선택합니다.
# sorted() 함수를 사용하여 선택된 정수 요소를 오름차순으로 정렬합니다.
# * 연산자를 사용하여 정렬된 요소를 인자로 전달합니다.
# sep='\n'을 사용하여 각 요소를 새 줄로 구분하여 출력합니다.