import itertools
from xml.dom.minidom import TypeInfo


def twoSum(arr):
    nums = itertools.combinations(arr, 2)
    for x, y in nums:
        if x + y == 6:
            if x == y:
                res = [idx for idx, val in enumerate(arr) if val in arr[:idx]]
                print(arr.index(x), res[0])
            else:
                print(arr.index(x), arr.index(y))


twoSum([3, 2, 3])
