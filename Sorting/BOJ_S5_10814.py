
# 230604

import sys

input = sys.stdin.readline

li = []

for i in range(int(input())):
    age, name = input().split()
    li.append({'age': int(age), 'name': name, 'date': i})

# lambda 활용 다중 정렬 #
# sorted sort 둘 다 가능
# sorted_li = sorted(li, key=lambda person: (person['age'], person['date']))
li.sort(key=lambda person: (person['age'], person['date']))

for person in li:
    print(person['age'], person['name'])