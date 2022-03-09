import sys
from collections import deque

input = sys.stdin.readline
n, m = map(int, input().split())
graph1 = []
graph2 = []
for _ in range(n):
    graph1.append(list(map(int, input().split())))

for _ in range(n):
    graph2.append(list(map(int, input().split())))
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(ax, ay):
    q = deque([(ax, ay)])
    copy = graph1[ax][ay]
    graph1[ax][ay] = graph2[ax][ay]

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue

            if graph1[nx][ny] == copy:
                graph1[nx][ny] = graph1[x][y]
                q.append((nx, ny))


is_break = False
for i in range(n):
    for j in range(m):
        if graph1[i][j] != graph2[i][j]:
            bfs(i, j)
            is_break = True
    if is_break == True:
        break

if graph1 == graph2:
    print("YES")
else:
    print("NO")
