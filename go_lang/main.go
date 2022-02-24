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

func hoit() {
	var n, x int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	fmt.Fscanln(reader, &n, &x)

	var arr = make([]int, n)
	for i := range arr {
		fmt.Fscanf(reader, "%d", &arr[i])
		if arr[i] < x {
			fmt.Fprintf(writer, "%d ", arr[i])
		}
	}
}

func back10952() {
	var a, b = 9, 9
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	for true {
		fmt.Fscanln(reader, &a, &b)
		if a == 0 || b == 0 {
			break
		}
		fmt.Fprintln(writer, a+b)
	}

}

func back10951() {
	var a, b int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	for true {
		val, _ := fmt.Fscanln(reader, &a, &b)
		if val != 2 {
			break
		}
		fmt.Fprintln(writer, a+b)
	}
}

func main() {
	var a, count int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	fmt.Fscanln(reader, &a)
	k := a
	for true {
		first := a / 10
		second := a % 10
		a = (second)*10 + (first+second)%10
		count++
		if a == k {
			break
		}
	}
	fmt.Println(count)
}
