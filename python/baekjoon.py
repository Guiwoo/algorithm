# 피보나치 수열 0과 1을 얼마나 사용했는지 확인

# 0 1 1 2 3 5

# fibo(0) = 0
# fibo(1) = 1
# fibo(2) = 2

T = int(input())
C = [int(input()) for _ in range(T)]

c0 = [1, 0]
c1 = [0, 1]

for i in C:
    if i == 0:
        print("1 0")
    elif i == 1:
        print("0 1")
    else:
        for j in range(2, i + 1):
            c0.append(c0[j - 1] + c0[j - 2])
            c1.append(c1[j - 1] + c1[j - 2])
        print(f"{c0.pop()} {c1.pop()}")
        c0 = [1, 0]
        c1 = [0, 1]
