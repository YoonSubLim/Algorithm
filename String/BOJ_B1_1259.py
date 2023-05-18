
# 230518

import sys

while True:
    wd = sys.stdin.readline().strip()
    if wd == '0':
        break

    wd_reverse = list(wd) # 공백없는 문자열 문자별로 쪼개 리스트화
    wd_reverse.reverse() # 리버스 하기 위해 리스트로 만든 것

    wd_reverse = ''.join(wd_reverse) # 쉬운 비교 위해 다시 string

    if wd == wd_reverse:
        print("yes")
    else:
        print("no")