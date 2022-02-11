# 10진수 to 2진수 how ?
# 01 ,0~9


def notation(base, n):
    if n < base:
        print(numberChar[n], end=" ")
    else:
        print("running", numberChar[n % base], n)
        notation(base, n // base)
        print(numberChar[n % base], end=" ")


numberChar = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
numberChar += ["A", "B", "C", "D", "E", "F"]

number = int(input("10"))
notation(2, number)
