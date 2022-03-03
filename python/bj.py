from collections import defaultdict
import string
import sys


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


def bj15996(arr):
    # 주어진 정수 array 합구하기
    # answer = 0 236ms
    # for i in range(arr):
    #     answer += arr[i]
    # return answer
    return sum(arr)
    # 60ms


def bj4673():
    # 점화식
    # d(n) < 10000
    # d(n) = d(n-1) + sum([int(i) for i in str(d(n-1))])
    natuarl_num = set(range(1, 101))
    generate_num = set()

    for i in range(1, 101):
        num = i + sum([int(i) for i in str(i)])
        generate_num.add(num)

    self_num = sorted(natuarl_num - generate_num)
    for i in self_num:
        print(i)


def bj1065():
    num = int(input())

    if num < 100:
        print(num)

    for i in range(100, num + 1):
        arr = [int(x) for x in str(i)]
        if arr[0] - arr[1] != arr[1] - arr[2]:
            num -= 1

    print(num)


def bj3058():
    x = int(input())
    for _ in range(x):
        arr = list(map(int, sys.stdin.readline().split()))
        even = [i for i in arr if i % 2 == 0]
        print(sum(even), min(even))


def bj11654():
    letter = input()
    print(ord(letter))


def bj11720():
    num = int(input())
    answer = [0] * num
    text = input()
    for i in range(len(answer)):
        answer[i] = int(text[i])
    print(sum(answer))


def bj10890():
    myDict = defaultdict(int)
    alpha = list(string.ascii_lowercase)
    for i in range(len(alpha)):
        myDict[alpha[i]] = -1
    text = input()
    for i in range(len(text)):
        if myDict[text[i]] == -1:
            myDict[text[i]] = i
    print(*list(myDict.values()))


bj10890()
