package main

import (
	"bufio"
	"fmt"
	"os"
)

// Bufio Way
/**
var length int
reader := bufio.NewReader(os.Stdin)
writer := bufio.NewWriter(os.Stdout)
fmt.Fscanln(reader, &length)

var a, b int
for i := 0; i < length; i++ {
fmt.Fscanln(reader, &a, &b)
fmt.Fprintln(writer, a+b)
}
writer.Flush()
*/

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

func main() {
	bj9202()
}
