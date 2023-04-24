
# BOJ 1919 애너그램

word1 = list(input())
word2 = list(input())

word_all = word1 + word2
word_all = list(set(word_all))

count = 0
for char in word_all:
    num1 = word1.count(char)
    num2 = word2.count(char)
    count += abs(num1-num2)
print(count)

