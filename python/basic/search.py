import random

Million = 1000000
Thousand = 1000
Num = 7878


def search(arr):
    count = 0
    for x in arr:
        if x == 7878:
            break
        count += 1
    return count


def Bsearch(arr, start, end, count=0):
    if start > end:
        return None
    mid = (start + end) // 2

    if arr[mid] == Num:
        return count
    elif arr[mid] > Num:
        end = mid - 1
    else:
        start = mid + 1
    return Bsearch(arr, start, end, count + 1)


data1M = [random.randint(0, 999999) for _ in range(10)]
data1M.insert(random.randint(0, 10), Num)
data1M_sorted = sorted(data1M)
print(data1M_sorted)
# sequence_search = search(data1M)
# print("Sequence Search :", sequence_search)
print(Bsearch(data1M_sorted, 0, len(data1M_sorted) - 1))
# print("Binary Search :", binary_search)
