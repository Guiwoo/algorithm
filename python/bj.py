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


bj11653_ver2()
