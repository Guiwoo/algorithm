# Dynamic Programming
"""
어떻게 해야 금광을 가장 많이 캘수 있는지
움직일수 있는 방향은 오른쪽 혹은 아래 이다.
시작점은 0,0 이고 탈출지점은 4,4 이다 
"""
table = [
    [1, 4, 4, 2, 2],
    [1, 3, 3, 0, 5],
    [1, 2, 4, 3, 0],
    [3, 3, 0, 4, 2],
    [1, 3, 4, 5, 3],
]


def main(table):
    # 오른쪽으로 쭉가는 경우 초기화
    for x in range(1, len(table[0])):
        table[0][x] += table[0][x - 1]
    # 아래쪽으로 쭉가는 경우 초기화
    for x in range(1, len(table)):
        table[x][0] += table[x - 1][0]
    # 왼쪽 위쪽 비교해서 높은거에 나자신을 더해
    for r in range(1, len(table)):
        for c in range(1, len(table[r])):
            highest = max(table[r][c - 1], table[r - 1][c])
            table[r][c] += highest

    # 길 트래킹 하자, 현재위치 를 표시해야지
    # 어떻게 가? 무조건 오른쪽 또는 아래로 근데 높은쪽으로만
    # 길방향
    currentX, currentY = 0, 0

    while True:
        table[currentY][currentX] = 0
        if currentX == 4 & currentY == 4:
            break
        elif currentX == 4:
            currentX, currentY = currentX, currentY + 1
        elif currentY == 4:
            currentX, currentY = currentX + 1, currentY
        else:
            right, down = table[currentY][currentX + 1], table[currentY + 1][currentX]
            if right > down:
                currentX, currentY = currentX + 1, currentY
            else:
                currentX, currentY = currentX, currentY + 1


main(table)

print(table)
