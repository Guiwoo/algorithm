def bj2231():
    def cals(n):
        total = int(n)
        for i in n:
            total += int(i)
        return total

    n = input()
    if (int(n) - 9 * len(n)) < 0:
        c = 0
    else:
        c = int(n) - 9 * len(n)
    answer = -1

    for i in range(c, int(n)):
        if cals(str(i)) == int(n):
            answer = i
            break

    if answer == -1:
        print(0)
    else:
        print(answer)


def bj7568():
    n = int(input())
    arr = [0] * n
    score = [0] * n
    for i in range(n):
        x, y = map(int, input().split())
        arr[i] = (x, y)

    for i in range(len(arr)):
        k = 0
        for j in range(len(arr)):
            x, y = arr[i]
            x2, y2 = arr[j]
            if x < x2 and y < y2:
                k += 1

        score[i] = k + 1

    print(*score)


def bj1018():
    N, M = map(int, input().split())
    board = list()
    for i in range(N):
        board.append(input())

    minimum = N * M
    for i in range(0, N - 7):
        for j in range(0, M - 7):
            cntChange1 = 0
            cntChange2 = 0
            for k in range(i, i + 8):
                for l in range(j, j + 8):
                    if (k + l) % 2 == 0:
                        if board[k][l] == "B":
                            cntChange1 += 1
                        else:
                            cntChange2 += 1
                    else:
                        if board[k][l] == "B":
                            cntChange2 += 1
                        else:
                            cntChange1 += 1
            if minimum > min(cntChange1, cntChange2):
                minimum = min(cntChange1, cntChange2)

    print(minimum)


def bj1436():
    n = int(input())

    count = 1
    title = 666
    current = 1666

    while count < n:
        temp = current
        while temp >= 666:
            if temp % 1000 == 666:
                count += 1
                break
            temp = temp // 10
        title = current
        current += 1

    print(title)


bj1436()
