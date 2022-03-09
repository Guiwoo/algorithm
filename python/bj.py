from collections import defaultdict
from collections import deque
import itertools
import math
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


def bj2675():
    letters = int(input())
    for _ in range(letters):
        answer = ""
        num, words = input().split()
        for i in words:
            answer += i * int(num)
        print(answer)


def bj1157():
    word = input().upper()
    word_list = list(set(word))

    cnt = []
    for i in word_list:
        count = word.count
        cnt.append(count(i))

    if cnt.count(max(cnt)) > 1:
        print("?")

    else:
        print(word_list[(cnt.index(max(cnt)))])


def bj1152():
    letters = input().strip()
    Larr = letters.split()
    count = 0
    for i in Larr:
        if i != "\n" and i != "":
            count += 1
    print(count)


def bj2908():
    num1, num2 = input().split()
    num1 = num1[::-1]
    num2 = num2[::-1]
    print(max([int(num1), int(num2)]))


def bj5622():
    arr = [
        3,
        3,
        3,
        4,
        4,
        4,
        5,
        5,
        5,
        6,
        6,
        6,
        7,
        7,
        7,
        8,
        8,
        8,
        8,
        9,
        9,
        9,
        10,
        10,
        10,
        10,
    ]
    letters = input()
    answer = 0
    for i in letters:
        answer += arr[ord(i) - 65]
    print(answer)


def bj2941():
    arr = ["c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="]
    string = input()
    count = 0
    while len(string) > 0:
        if string[0:2] in arr:
            count += 1
            string = string[2:]
        elif string[0:3] in arr:
            count += 1
            string = string[3:]
        else:
            count += 1
            string = string[1:]
    print(count)


def bj1316():
    num = int(input())
    answer = 0
    for _ in range(num):
        check = []
        current = ""
        value = True
        for i in input():
            if i != current and i not in check:
                check.append(i)
                current = i
            if i in check and i != current and i != check[-1]:
                value = False
                break
        if value:
            answer += 1
    print(answer)


def bj1712():
    [a, b, c] = list(map(int, sys.stdin.readline().split()))

    if a > c and b > c or b == c:
        print(-1)
    else:
        answer = (a / (c - b)) + 1
        print(int(answer))


def bj2292():
    num = int(input())
    current = 1
    answer = 0
    while current < num:
        answer += 1
        current += 6 * answer

    print(answer + 1)


def bj1193():
    target = int(input())
    current = 1
    count = 2

    if target == 1:
        print(1, 1)
        return

    while current < target:
        current += count
        count += 1

    line = count - 2
    diff = current - target
    if line % 2 != 0:
        print(line + 1 - diff, 1 + diff)
    else:
        print(1 + diff, line + 1 - diff)


def bj2869():
    [x, y, v] = list(map(int, input().split()))
    if v <= x:
        print(1)
        return
    answer = (v - x) / (x - y)
    if answer < 1:
        print(math.ceil(answer + 1))
    else:
        print(math.ceil(answer) + 1)


def bj2869_copy():
    [a, b, v] = list(map(int, input().split()))
    day = 1
    if (v - a) % (a - b) == 0:
        day += (v - a) / (a - b)
    else:
        day += (v - a) / (a - b) + 1


def bj10250():
    given = int(input())
    for _ in range(given):
        h, w, n = map(int, input().split())
        height = n % h
        if height == 0:
            height = h
            row = n // h
        else:
            row = n // h + 1

        if row < 10:
            print(f"{height}0{row}")
        else:
            print(f"{height}{row}")


def makeNewSum(arr):
    newArr = [0] * len(arr)
    for i in range(len(arr)):
        newArr[i] = sum(arr[: i + 1])
    return newArr


def bj2775():
    num = int(input())

    for _ in range(num):
        n = int(input())
        k = int(input())

        arr = [x * (x + 1) // 2 for x in range(1, k + 1)]
        # for loop 돌면서 더하자
        if n == 1:
            print(arr[k - 1])
        else:
            for _ in range(1, n):
                arr = makeNewSum(arr)
            print(arr[len(arr) - 1])


def bj22352():
    ROW, COLUMN = map(int, input().split())

    graph = []

    for _ in range(1, 3):
        for i in range(ROW):
            graph.append(list(map(int, input().split())))

    graph1 = graph[:ROW]
    graph2 = graph[ROW:]

    flag = True
    for r in range(ROW):
        for c in range(COLUMN):
            if graph1[r][c] != graph2[r][c]:
                bfs(r, c, graph2[r][c], graph1[r][c], graph1, ROW, COLUMN)
                flag = False
        if flag == False:
            break

    if graph1 == graph2:
        print("YES")
    else:
        print("NO")


def bfs(r, c, target, current, graph, ROW, COLUMN):
    queue = deque([(r, c)])
    visited = [[False] * COLUMN for _ in range(ROW)]
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    while queue:
        x, y = queue.popleft()
        graph[x][y] = target

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or ny < 0 or nx >= ROW or ny >= COLUMN:
                continue
            if (
                graph[nx][ny] == current
                and graph[nx][ny] != target
                and visited[nx][ny] != True
            ):
                visited[nx][ny] = True
                queue.append((nx, ny))


bj22352()
