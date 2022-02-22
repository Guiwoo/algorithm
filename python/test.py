def arrayManipulation(n, queries):
    # delimit each interval [a, b] by saving
    # in deltas the difference that such
    # interval has in respect of adjacent areas
    deltas = [0] * (n + 1)
    for a, b, k in queries:
        deltas[a - 1] += k
        deltas[b] -= k
    # curr is like a walker going through mountains,
    # and peek is the maximum height he/she reached
    curr, peek = 0, 0
    for k in deltas:
        curr += k
        peek = max(peek, curr)
    return peek
