# 2022 KAKAO TECH INTERNSHIP
# https://school.programmers.co.kr/learn/courses/30/lessons/118666

# 231122

def solution(survey, choices):
    answer = ''

    MBTI = {'R': 0, 'T': 0, 'C': 0, 'F': 0, 'J': 0, 'M': 0, 'A': 0, 'N': 0}

    for i in range(len(survey)):
        score = choices[i]

        if score == 4:
            continue

        if score < 4:
            MBTI[survey[i][0]] += 4 - score

        if score > 4:
            MBTI[survey[i][1]] += score - 4

    if MBTI['R'] >= MBTI['T']:
        answer += "R"
    else:
        answer += "T"

    if MBTI['C'] >= MBTI['F']:
        answer += "C"
    else:
        answer += "F"

    if MBTI['J'] >= MBTI['M']:
        answer += "J"
    else:
        answer += "M"

    if MBTI['A'] >= MBTI['N']:
        answer += "A"
    else:
        answer += "N"
    return answer
