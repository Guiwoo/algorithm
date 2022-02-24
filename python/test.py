from typing import Type


def arrayManipulation(n, queries):
    # delimit each interval [a, b] by saving
    # in deltas the difference that such
    # interval has in respect of adjacent areas
    deltas = [0] * (n + 1)
    for a, b, k in queries:
        deltas[a - 1] += k
        deltas[b] -= k
    # curr is like a walker going through mountains,
    # and peek is the maximum height he/she reached
    curr, peek = 0, 0
    for k in deltas:
        curr += k
        peek = max(peek, curr)
    return peek


x = int(input())
count = 0
newX = None
while True:
    if x < 0 and newX == None:
        newX = x * 10 + x
        count += 1
        continue
    if newX == None:
        newX = (x % 10) * 10 + (x // 10 + x % 10) % 10
    else:
        newX = (newX % 10) * 10 + (newX // 10 + newX % 10) % 10

    count += 1
    if x == newX:
        break

print(count)
