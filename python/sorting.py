def fInsert(arr):
    n = len(arr)
    for end in range(1, n):
        for cur in range(end, 0, -1):
            if arr[cur - 1] > arr[cur]:
                arr[cur - 1], arr[cur] = arr[cur], arr[cur - 1]
    return arr


# before = [188, 162, 168, 50, 120, 150, 170, 105]
# print("Before", before)
# before = fInsert(before)
# print("After", before)

# 문제 정렬해서 낮은사람 높은사람 순으로 배열 하기
Before = [["a", 88], ["b", 99], ["c", 71], ["d", 78], ["e", 67], ["f", 92]]

Before.sort(key=lambda x: x[1])

for i in range(len(Before) // 2):
    # print(Before[i], Before[len(Before) - 1 - i])
    pass


# 문제 2차원 배열 값에서 중앙 값 찾기
import itertools

Ginput = [[55, 33, 250, 44], [88, 1, 67, 23], [199, 222, 38, 47], [155, 145, 20, 99]]
me = sum(Ginput, [])
# print(list(itertools.chain(*Ginput)))
# print(list(itertools.chain.from_iterable(Ginput)))
# print(me)
me.sort()
print(me[len(me) // 2])
