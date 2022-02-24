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


def bj10181():
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


def bj10818():
    n = int(input())
    arr = list(map(int, input().split()))
    print(f"{min(arr)} {max(arr)}")


def bj2562():
    highest = 0
    count = 0
    for i in range(1, 10):
        n = int(input())
        if n > highest:
            highest = n
            count = i
    print(highest, count, sep="\n")


def bj2575():
    a = 1
    arr = [0] * 10
    for _ in range(3):
        n = int(input())
        a *= n

    for x in str(a):
        arr[int(x)] += 1

    print(*arr, sep="\n")


def bj3052():
    a = 42
    mSet = set()
    for _ in range(10):
        n = int(input())
        mSet.add(n % a)

    print(mSet)
    print(len(mSet))


def bj1546():
    total = 0
    n = int(input())
    arr = list(map(int, input().split()))

    high = max(arr)
    for i in arr:
        total += float(i * 100) / float(high)
    print(total / n)


def bj8958():
    num = int(input())
    for _ in range(num):
        total = 0
        count = 0
        arr = [*input()]
        for i in arr:
            if i == "O":
                count += 1
                total += count
            else:
                count = 0
        print(total)


bj8958()
