package bj

import (
	"bufio"
	"fmt"
	"math"
	"os"
)

// Checking Specifif PrimeNumber
func bj1978() {
	checking := func(number int) bool {
		if number == 1 {
			return false
		}
		for i := 2; i < number; i++ {
			if number%i == 0 {
				return false
			}
		}
		return true
	}
	var n int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n)
	count := 0
	for i := 0; i < n; i++ {
		var a int
		fmt.Fscan(reader, &a)
		if checking(a) {
			count++
		}
	}
	fmt.Println(count)
}

// Find all PrimeNumber and compare
func bj1978_ver2() {
	var decimal [1001]bool
	makeDecimal := func() {
		for i := range decimal {
			decimal[i] = true
		}
		decimal[0] = false
		decimal[1] = false

		for i := 2; i < 1001; i++ {
			if decimal[i] {
				for j := 2; i*j < 1001; j++ {
					decimal[i*j] = false
				}
			}
		}
	}
	makeDecimal()

	var n, count int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n)
	arr := make([]int, n)
	for i, _ := range arr {
		var num int
		fmt.Fscan(reader, &num)
		arr[i] = num
	}
	for _, v := range arr {
		if decimal[v] {
			count++
		}
	}
	fmt.Println(count)
}

func bj2581() {
	//Prime Checking
	isPrime := func(num int) bool {
		if num == 1 {
			return false
		}
		for j := 2; j < num; j++ {
			if num%j == 0 {
				return false
			}
		}
		return true
	}
	//GetSum
	getSum := func(arr []int) int {
		total := 0
		for _, v := range arr {
			total += v
		}
		return total
	}

	var a, b int
	arr := []int{}
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &a)
	fmt.Fscanln(reader, &b)
	for i := a; i < b+1; i++ {
		if isPrime(i) {
			arr = append(arr, i)
		}
	}
	if len(arr) < 1 {
		fmt.Println(-1)
	} else {
		total := getSum(arr)
		fmt.Println(arr[0], total)
	}
}

func bj11652() {
	var n, i int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n)
	if n == 1 {
		return
	}
	i = 2
	for i*i <= n {
		if n%i == 0 {
			fmt.Println(i)
			n /= i
		} else {
			i++
		}
	}
	print(n)
}

//에라토네스 체 구하기 나만의 방법
func bj1929() {
	var a, b int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &a, &b)

	isPrime := func(num int) bool {
		if num == 1 {
			return false
		}
		for j := 2; j*j <= num; j++ {
			if num%j == 0 {
				return false
			}
		}
		return true
	}
	arr := make([]int, b-a+1)
	for i := 0; i < len(arr); i++ {
		arr[i] = a + i
		if arr[i] == 1 || arr[i] == -1 {
			continue
		}
		if isPrime(arr[i]) {
			fmt.Println(arr[i])
			for j := 1; j*arr[i]+i < len(arr); j++ {
				arr[j*arr[i]+i] = -1
			}
		}
	}
}

//에라토네스 체 구하기 고랭 배운방법
func bj1929_ver2() {
	var decimal []bool
	var minV, maxV int
	isPrime := func(n int) {
		for i := 0; i <= n; i++ {
			decimal = append(decimal, true)
		}
		decimal[0] = false
		decimal[1] = false
		for i := 2; i <= n; i++ {
			if decimal[i] {
				for j := 2; i*j <= n; j++ {
					decimal[i*j] = false
				}
			}
		}
	}

	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	fmt.Fscanf(reader, "%d %d", &minV, &maxV)
	isPrime(maxV)
	for i := minV; i <= maxV; i++ {
		if decimal[i] {
			fmt.Fprintln(writer, i)
		}
	}
}

//에라토네스 체 구하기 파이썬 방법 응용
func bj1920_ver3() {
	var minV, maxV int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Fscanf(reader, "%d %d", &minV, &maxV)
	arr := make([]bool, maxV+1)
	arr[0], arr[1] = true, true
	for i := 2; i*i < maxV; i++ {
		if !arr[i] {
			for j := i + i; j < len(arr); j = j + i {
				arr[j] = true
			}
		}
	}
	for k := minV; k < maxV; k++ {
		if !arr[k] {
			fmt.Println(k)
		}
	}
}

func bj4948() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	for {
		var a, count int
		fmt.Fscanln(reader, &a)
		if a == 0 {
			break
		}
		arr := make([]bool, a*2+1)
		arr[0], arr[1] = true, true
		for i := 2; i*i < a*2+1; i++ {
			if !arr[i] {
				for j := i + i; j < len(arr); j = j + i {
					arr[j] = true
				}
			}
		}
		for i := a + 1; i < a*2+1; i++ {
			if !arr[i] {
				count++
			}
		}
		fmt.Println(count)
	}
}

func bj9202() {

	primeList := func() []bool {
		arr := make([]bool, 10001)
		arr[0], arr[1] = true, true
		for i := 2; i < 10001; i++ {
			if !arr[i] {
				for j := i + i; j < 10001; j = j + i {
					arr[j] = true
				}
			}
		}
		return arr
	}

	var num int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Fscanln(reader, &num)
	arr := primeList()
	for i := 0; i < num; i++ {
		var target int
		fmt.Fscanln(reader, &target)
		for j := target / 2; j > 1; j-- {
			if !arr[j] && !arr[target-j] {
				fmt.Fprintln(writer, j, target-j)
				break
			}
		}
	}
}

func bj1085() {
	minV := func(num1 int, num2 int) int {
		if num1 > num2 {
			return num2
		}
		return num1
	}
	var x, y, w, h int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Fscanln(reader, &x, &y, &w, &h)
	minX := minV(x, w-x)
	minY := minV(y, h-y)
	fmt.Fprintln(writer, minV(minX, minY))
}

func bj3009() {

	reader := bufio.NewReader(os.Stdin)

	arrX := map[int]int{}
	arrY := map[int]int{}

	for i := 0; i < 3; i++ {
		var a, b int
		fmt.Fscanln(reader, &a, &b)
		arrX[a] += 1
		arrY[b] += 1
	}
	var x, y int
	for i, v := range arrX {
		if v != 2 {
			x = i
		}
	}
	for i, v := range arrY {
		if v != 2 {
			y = i
		}
	}
	fmt.Println(x, y)
}

func bj4153() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	for {
		var a, b, c int
		fmt.Fscanln(reader, &a, &b, &c)
		if a == 0 {
			break
		} else {
			a *= a
			b *= b
			c *= c
			if a+b == c || a+c == b || b+c == a {
				fmt.Fprintln(writer, "right")
			} else {
				fmt.Fprintln(writer, "wrong")
			}
		}
	}
}

func bj3052() {
	var r float64
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &r)

	pi := math.Pi

	fmt.Printf("%.6f\n%.6f", r*r*pi, r*r*2)
}

func bj1002() {
	var num int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &num)
	for i := 0; i < num; i++ {
		var x1, y1, r1, y2, x2, r2 int
		fmt.Fscanf(reader, "%d %d %d %d %d %d\n", &x1, &y1, &r1, &x2, &y2, &r2)

		var distanceX = x1 - x2
		var distanceY = y1 - y2

		var addR = math.Pow(float64(r1+r2), 2)
		var subR = math.Pow(float64(r1-r2), 2)

		var d = math.Pow(float64(distanceX), 2) + math.Pow(float64(distanceY), 2)

		if d < addR && d > subR {
			fmt.Println(2)
		} else if d == addR || d == subR && d != 0 {
			fmt.Println(1)
		} else if d < subR || d > addR {
			fmt.Println(0)
		} else if d == 0 && r1 == r2 {
			fmt.Println(-1)
		}
	}
}
