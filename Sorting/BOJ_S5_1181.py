from sys import stdin

words = set()
for _ in range(int(stdin.readline().strip())):
    word = stdin.readline().strip()
    words.add(word)


# def sort_key(word):
#     print("word is " + word)
#     return len(word), word  # 글자 수, 사전 순의 key 반환

# or

sorted_list = sorted(words, key=lambda x: (len(x), x))

for item in sorted_list:
    print(item)

# print(*sorted_list, sep="\n")
# list(map(print, sorted_list))
# print('\n'.join(item for item in sorted_list))
