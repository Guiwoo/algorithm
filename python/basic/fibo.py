# 피보나치 수열 높은 위치 에 있을떄의 값 구하기
# 30 번쨰 자리를 구할려면 재귀를 사용시 약 260만번 연산 동적계획법은 ? 30번이내 연산 시간으로 비교해보자
import time

# 재귀적으로 어떻게 ?
def recursive(num):
    if num == 0:
        return 0
    elif num == 1:
        return 1
    return recursive(num - 1) + recursive(num - 2)


start_time = time.time()
fiboT = recursive(35)
end_time = time.time()
print(
    f"Recursive Total Time is {end_time - start_time},✅ {fiboT}",
)

# 다이나믹 방법
dp = [0] * 100
dp[0] = 0
dp[1] = 1


def dynamicFibo(num):
    for i in range(2, num + 1):
        dp[i] = dp[i - 1] + dp[i - 2]
    return dp[num]


start_time2 = time.time()
dpT = dynamicFibo(35)
end_time2 = time.time()
print(
    f"Dynamic Total Time is {end_time2 - start_time2},✅ {dpT}",
)
