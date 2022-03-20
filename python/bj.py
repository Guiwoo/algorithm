from collections import defaultdict
from dis import dis
import sys
import math


def bj1978():
    n = int(input())
    primeNumber = list(map(int, input().split()))

    count = 0

    def isPrime(theNumber):
        if theNumber == 1:
            return False
        for i in range(2, theNumber):
            if theNumber % i == 0:
                return False
        return True

    for i in range(len(primeNumber)):
        if isPrime(primeNumber[i]):
            count += 1

    print(count)


def bj2581():

    # isPrime Check
    def isPrime(number):
        if number == 1:
            return False
        for i in range(2, number):
            if number % i == 0:
                return False
        return True

    minV = int(input())
    maxV = int(input())
    arr = []

    for i in range(minV, maxV + 1):
        if isPrime(i):
            arr.append(i)

    if len(arr) < 1:
        return print(-1)

    print(f"{sum(arr)}\n{arr[0]}")


def bj11653():
    # Checking Is Prime or not
    def isPrime(number):
        if number == 1:
            return False
        for i in range(2, math.floor(math.sqrt(number))):
            if number % i == 0:
                return False
        return True

    # Return value For Next Prime
    def nextPrime(number):
        current = number + 1
        while True:
            if isPrime(current):
                break
            current += 1
        return current

    n = int(input())
    if isPrime(n):
        return print(n)
    i = 2

    while n > 1:
        if n % i == 0:
            n = n // i
            print(i)
        else:
            i = nextPrime(i)


def bj11653_ver2():
    n = int(input())
    if n == 1:
        return
    i = 2
    while i * i <= n:
        if n % i == 0:
            print(i)
            n //= i
        else:
            i += 1
    print(n)


# 에라토네스 체 구현하기
def bj1929():
    (a, b) = map(int, input().split())

    arr = [True] * (b + 1)
    arr[0], arr[1] = False, False

    for i in range(2, int(b**0.5) + 1):
        if arr[i]:
            for j in range(i + i, len(arr), i):
                arr[j] = False

    for i in range(a, b + 1):
        if arr[i] == True:
            print(i)


def bj4948():
    while True:
        count = 0
        num = int(input())
        if num == 0:
            break
        arr = [True] * (num * 2 + 1)
        arr[0], arr[1] = False, False

        for i in range(2, int(num * 2**0.5) + 1):
            if arr[i]:
                for j in range(i + i, len(arr), i):
                    arr[j] = False

        print(arr[num + 1 : num * 2 + 1].count(True))


def bj9020():
    amIPrime = [False] + [False] + [True] * 9999
    for i in range(2, 101):
        if amIPrime[i]:
            for j in range(i * i, 10001, i):
                amIPrime[j] = False
    num = int(input())
    for i in range(num):
        target = int(input())

        a = target // 2
        b = a

        while True:
            if amIPrime[a] and amIPrime[b]:
                print(f"{a} {b}")
                break
            a -= 1
            b += 1


def bj9020_ver2():
    prime = [1 for i in range(10001)]
    prime[0], prime[1] = 0, 0

    num = 2
    while num <= int(10001 ** (0.5)):
        if prime[num]:
            for i in range(num + num, 10001, num):
                prime[i] = 0
        num += 1

    T = int(sys.stdin.readline())
    for test in range(T):
        n = int(sys.stdin.readline())
        for i in range(n // 2, 2, -1):
            if prime[i] and prime[n - i]:
                print(i, n - i)
                break


def bj1085():
    x, y, w, h = map(int, input().split())
    print(min(x, y, w - x, h - y))


def bj3009():
    myDictX = defaultdict(int)
    myDictY = defaultdict(int)
    for i in range(3):
        x, y = map(int, input().split())
        myDictX[x] += 1
        myDictY[y] += 1

    for i in myDictX.keys():
        if myDictX[i] != 2:
            print(i, end=" ")

    for i in myDictY.keys():
        if myDictY[i] != 2:
            print(i)


def bj4153():
    while True:
        a, b, c = map(int, sys.stdin.readline().split())
        if a == 0:
            break
        big = max(a, b, c)
        if (
            a * a + b * b == big * big
            or b * b + c * c == big * big
            or a * a + c * c == big * big
        ):
            sys.stdout.writelines("right\n")
        else:
            sys.stdout.writelines("wrong\n")


def bj3053():
    r = int(input())
    print(f"{math.pi*r**2:.6f}\n{r**2*2:.6f}")


def bj1002():
    n = int(input())
    for i in range(n):
        arr = list(map(int, input().split()))
        # arr = [x1,y1,r1,x2,y2,r2]
        disX = arr[0] - arr[3]
        disY = arr[1] - arr[4]

        addR = (arr[2] + arr[5]) ** 2
        subR = (arr[2] - arr[5]) ** 2

        d = (disX) ** 2 + (disY) ** 2

        if d < addR and d > subR:
            print(2)
        elif d == addR or d == subR and d != 0:
            print(1)
        elif d < subR or d > subR:
            print(0)
        elif d == 0 and arr[2] == arr[5]:
            print(-1)


bj1002()
