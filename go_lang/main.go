package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"reflect"
	"strconv"
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

//while 처럼 쓰기
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

//최대값 찾기 ~
func bj2562() {
	var a int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	fmt.Fscanln(reader, &a)

	var arr = make([]int, a)
	for i := range arr {
		fmt.Fscanf(reader, "%d", &arr[i])
	}
	min := arr[0]
	max := arr[0]
	for _, value := range arr {
		if value < min {
			min = value
		}
		if value > max {
			max = value
		}
	}
	fmt.Println(min, max)
}

func bj2577() {
	a := 1
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	var arr = make([]int, 10)
	for i := 0; i < 3; i++ {
		var b int
		fmt.Fscanln(reader, &b)
		a = a * b
	}
	s := strconv.Itoa(a)
	for _, v := range s {
		v := string(v)
		i, _ := strconv.Atoi(v)
		arr[i] += 1
	}
	for _, v := range arr {
		fmt.Println(v)
	}
}

//여러값주고 안에서 나머지 값 찾기
func bj3052() {
	a := 42
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	var arr = make(map[int]bool)
	for i := 0; i < 10; i++ {
		var b int
		fmt.Fscanln(reader, &b)
		arr[b%a] = true
	}
	fmt.Println(arr)
	fmt.Println(len(arr))
}

func bj1546() {
	var a int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	fmt.Fscanln(reader, &a)
	defer writer.Flush()

	var arr = make([]float64, a)
	var high float64
	for i, _ := range arr {
		fmt.Fscanf(reader, "%v", &arr[i])
		if arr[i] > high {
			high = arr[i]
		}
	}
	var total float64
	for _, v := range arr {
		total += (v * 100) / high
	}
	fmt.Println(total / float64(a))
}

func bj8958() {
	var a int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	fmt.Fscanln(reader, &a)
	defer writer.Flush()
	for i := 0; i < a; i++ {
		var count, total int
		var s string
		fmt.Fscanln(reader, &s)
		for _, v := range s {
			if string(v) == "O" {
				count++
				total += count
			} else {
				count = 0
			}
		}
		fmt.Println(total)
	}
}

//스페이스 하나 띄어쓰기 안해가지고 아 죽여주세요ㅠㅠ...
func bj4344() {
	var c int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	fmt.Fscanln(reader, &c)
	defer writer.Flush()

	for i := 0; i < c; i++ {
		var n int
		fmt.Fscanf(reader, "%d ", &n)

		var scores = make([]float64, n)
		var sum, avg float64

		for j := 0; j < n; j++ {
			fmt.Fscanf(reader, "%f ", &scores[j])
			sum += scores[j]
		}
		avg = sum / float64(n)

		var proportion float64
		for _, val := range scores {
			if val > avg {
				proportion += (1 / float64(n))
			}
		}
		fmt.Fprintf(writer, "%.3f%s\n", math.Round(proportion*100000)/1000, "%")
	}
}

//정수합 구하기 리스트 안에서
func Reduce(source, initialValue, reducer interface{}) (interface{}, error) {
	srcV := reflect.ValueOf(source)
	rv := reflect.ValueOf(reducer)
	accumulator := initialValue
	accV := reflect.ValueOf(accumulator)
	for i := 0; i < srcV.Len(); i++ {
		entry := srcV.Index(i)
		// call reducer via reflection
		reduceResults := rv.Call([]reflect.Value{
			accV,               // send accumulator value
			entry,              // send current source entry
			reflect.ValueOf(i), // send current loop index
		})
		accV = reduceResults[0]
	}
	return accV.Interface(), nil
}

func Bj15596(arr []int) int {
	sumOfInt := func(accumulator, entry, idx int) int {
		return accumulator + entry
	}

	v, _ := Reduce(arr, 0, sumOfInt)
	return v.(int)
}

//셀프넘버
func bj4673() {
	selfNumber := make(map[int]bool, 10001)
	for i := 0; i < 10000; i++ {
		selfNumber[i] = false
	}
	for i := 0; i < 10001; i++ {
		sum := i
		number := i
		for j := number; j != 0; j /= 10 {
			sum += j % 10
		}
		if sum <= 10000 {
			selfNumber[sum] = true
		}
	}
	for i := 1; i < len(selfNumber); i++ {
		if selfNumber[i] == false {
			fmt.Println(i)
		}
	}
}

func main() {
	bj4673()
}
