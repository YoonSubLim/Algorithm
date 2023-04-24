count = int(input())
words = list()

for i in range(count):
    words.append(input())

for word in words:
    first = word.split()[0]
    second = word.split()[1]

    if len(first) != len(second):
        print("Impossible")
        continue

    first_list = [0] * 26
    second_list = [0] * 26

    for char in first:
        first_list[ord(char) - ord('a')] += 1
    for char in second:
        second_list[ord(char) - ord('a')] += 1

    if first_list == second_list:
        print("Possible")
    else:
        print("Impossible")





