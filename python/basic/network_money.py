import itertools

Checking_list = list(itertools.combinations(["1", "2", "3", "4", "5", "6"], 3))

num = 6  # 인풋받았다 치고
Name = [0, "서울", "뉴욕", "북경", "방콕", "파리", "런던"]


def dfs(start, visit, graph):
    visit[start] = 1
    for w in range(len(graph[start])):
        if graph[start][w] != 0 and visit[w] == 0:
            dfs(w, visit, graph)

    if visit.count(1) == 6:  # 모든 도시를 방문해야 하니깐
        return True
    else:
        return False


answers = []

for Xnum, Ynum, Znum in Checking_list:
    line = [
        (0, 0, 0),
        (1, 2, 80),
        (1, 3, 10),
        (2, 3, 40),
        (2, 4, 70),
        (3, 4, 50),
        (4, 5, 30),
        (4, 6, 20),
        (5, 6, 60),
    ]  # input 받았다고 하고
    visit = [0] * (num + 1)
    line.pop(int(Xnum)),
    line.pop(int(Ynum) - 1),
    line.pop(int(Znum) - 2)

    graph = [[0] * (num + 1) for _ in range(len(line) + 1)]

    for x, y, c in line:
        graph[x][y] = graph[y][x] = c

    result = dfs(1, visit, graph)
    if result:
        total = 0
        for _, _, c in line:
            total += c
        answers.append((total, graph))
    else:
        continue

networkSpeed, cities = max(answers)
print("네트워크 최고 스피드", networkSpeed)


def Ndfs(num, f=[]):
    f.append(num)
    print(Name[num], end="->")
    for w in range(len(cities[num])):
        if cities[num][w] != 0 and w not in f:
            Ndfs(w, f)


Ndfs(1)
