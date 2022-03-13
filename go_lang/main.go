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

func main() {
	bj11652()
}
