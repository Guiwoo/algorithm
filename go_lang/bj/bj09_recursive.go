package bj

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
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

func bj11729() {
	var n int

	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	fmt.Fscanln(reader, &n)

	hanoi := func(n, frm, to, otr int) {}
	hanoi = func(n, frm, to, otr int) {
		if n < 2 {
			fmt.Fprintln(writer, frm, to)
			return
		}
		hanoi(n-1, frm, otr, to)
		fmt.Fprintln(writer, frm, to)
		hanoi(n-1, otr, to, frm)
	}
	fmt.Fprintln(writer, int(math.Pow(2, float64(n))-1))
	hanoi(n, 1, 3, 2)
}

func bj11729_ver2() {
	hanoi := func(n int, from byte, to byte, byteSlice *[]byte) {}
	hanoi = func(n int, from, to byte, byteSlice *[]byte) {
		left := 150 - from - to
		if n == 1 {
			*byteSlice = append(*byteSlice, from, ' ', to, '\n')
			return
		}
		hanoi(n-1, from, left, byteSlice)
		*byteSlice = append(*byteSlice, from, ' ', to, '\n')
		hanoi(n-1, left, to, byteSlice)
	}

	reader := bufio.NewReader(os.Stdin)
	text, _ := reader.ReadString('\n')
	split := strings.Fields(text)
	N, _ := strconv.Atoi(split[0])
	count := 1<<uint(N) - 1 // 2의 제곱을 이런식으로 표현 가능 오우 쉣 반대화살표는 루트 ?가될수도있고아닐수도 있고
	fmt.Println(count)
	result := make([]byte, 0, count*4) //왜 이만큼의 사이즈를 만들었는지 모르겠음
	pt := &result
	hanoi(N, '1', '3', pt)
	fmt.Println(string(result))
}
