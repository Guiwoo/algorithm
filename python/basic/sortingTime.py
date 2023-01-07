import random
import time

# 선택정렬
def selectionSort(arr):
    n = len(arr)
    for i in range(0, n - 1):
        minIdx = i
        for k in range(i + 1, n):
            if arr[minIdx] > arr[k]:
                minIdx = k
        arr[i], arr[minIdx] = arr[minIdx], arr[i]

    return arr


# 퀵정렬 책
def quickSort_book(arr, start, end):
    if end <= start:
        return
    low = start
    high = end

    pivot = arr[(low + high) // 2]
    while low <= high:
        while arr[low] < pivot:
            low += 1
        while arr[high] > pivot:
            high -= 1
        if low <= high:
            arr[low], arr[high] = arr[high], arr[low]
            low, high = low + 1, high - 1
    mid = low

    quickSort_book(arr, start, mid - 1)
    quickSort_book(arr, mid, end)


# 퀵정렬 책
def quick(arr):
    quickSort_book(arr, 0, len(arr) - 1)


# 퀵정렬
def quickSort(arr):
    # 리스트가 하나 이하 원소 만을 담고 있다면 종료
    if len(arr) <= 1:
        return arr
    # 기준 값 정해서 왼쪽 가운데값 오른쪽 만들고
    pivot = arr[0]
    tail = arr[1:]

    left_side = [x for x in tail if x <= pivot]
    right_side = [x for x in tail if x > pivot]
    return quickSort(left_side) + [pivot] + quickSort(right_side)


def main():
    counts = [1000, 10000]
    for count in counts:
        DataArr = [random.randint(1, count) for _ in range(count)]
        # start_time = time.time()
        # sSort = selectionSort(DataArr)
        # end_time = time.time()
        # print("Selection Sort %d", end_time - start_time)
        start_time = time.time()
        quickSort(DataArr)
        end_time = time.time()
        print("Quick Sort %d", end_time - start_time)


# 이미 정렬된 줄에 끼어들기
def bSort(arr):
    for i in range(len(arr) - 1, 0, -1):
        for j in range(i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr


def main2():
    temp = [random.randint(10000, 99999) for _ in range(10000)]
    temp.sort()
    rndPos = random.randint(0, len(temp) - 1)
    temp.insert(rndPos, temp[-1])

    start = time.time()
    bubble = bSort(temp)
    end = time.time()
    print(f"Bulbble {end-start}")
    print()
    start = time.time()
    # thing = quickSort(temp)
    book = quick(temp)
    end = time.time()
    print(f"Quick {end-start}")


main2()
