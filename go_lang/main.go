package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"

	"github.com/guiwoo/go_lang/tucker"
)

func bj10872() {
	var a int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &a)

	total := 1
	var fct = func(n int) {}
	fct = func(n int) {
		if n < 1 {
			return
		}
		total *= n
		fct(n - 1)
	}
	fct(a)
	fmt.Println(total)
}

func bj10870() {
	var a int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &a)

	fibo := func(n int) int { return 0 }
	fibo = func(n int) int {
		if n == 0 {
			return 0
		} else if n == 1 {
			return 1
		}
		return fibo(n-1) + fibo(n-2)
	}
	fmt.Println(fibo(a))
}

func bj2447() {
	var n int
	basic := []string{"***", "* *", "***"}
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n)

	paintStart := func(num int, arr []string) []string { return basic }

	paintStart = func(num int, arr []string) []string {
		sub := []string{}
		if num == 3 {
			return arr
		} else {
			for _, v := range arr {
				sub = append(sub, strings.Repeat(v, 3))
			}
			for _, v := range arr {
				sub = append(sub, v+strings.Repeat(" ", len(arr))+v)
			}
			for _, v := range arr {
				sub = append(sub, strings.Repeat(v, 3))
			}
			return paintStart(num/3, sub)
		}
	}

	answer := paintStart(n, basic)
	for _, v := range answer {
		fmt.Println(v)
	}
}

func main() {
	tucker.GuessNum100()
}
