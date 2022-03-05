package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"reflect"
	"strconv"
	"strings"
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

func getCount(a int) (answer int) {
	if a < 100 {
		return a
	}
	for i := 100; i <= a; i++ {
		one := i % 10
		ten := i / 10 % 10
		hund := i / 100
		if hund-ten == ten-one {
			answer++
		}
	}
	answer += 99
	return answer
}

func bj1065() {
	var a int
	fmt.Scanln(&a)
	var count = getCount(a)
	fmt.Println(count)
}

func bj11654() {
	var a string
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	fmt.Fscanln(reader, &a)
	asicc := []byte(a)
	fmt.Println(asicc[0])
}

func bj11720() {
	var n int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	fmt.Fscanln(reader, &n)
	defer writer.Flush()
	var text string
	fmt.Fscanln(reader, &text)
	var arr = make([]int, n)

	for i, _ := range arr {
		b, _ := strconv.Atoi(string(text[i]))
		arr[i] = b
	}
	n = 0
	for _, v := range arr {
		n += v
	}
	fmt.Println(n)
}

func bj10809() {
	var word string
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Fscanln(reader, &word)

	arr := map[int]int{}
	for i := 97; i < 123; i++ {
		arr[i] = -1
	}
	for i, v := range word {
		if arr[int(v)] == -1 {
			arr[int(v)] = i
		}
	}
	for i := 97; i < 123; i++ {
		fmt.Printf("%v ", arr[i])
	}
}

func bj2675() {
	var a int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	fmt.Fscanln(reader, &a)
	for i := 0; i < a; i++ {
		fmt.Println("??")
		var (
			num    int
			word   string
			answer string
		)
		fmt.Fscanln(reader, &num, &word)
		for _, v := range word {
			answer += strings.Repeat(string(v), num)
		}
		fmt.Println(answer)
	}
}

func bj1157() {
	var a string
	reader := bufio.NewReader(os.Stdin)

	fmt.Fscanln(reader, &a)
	var arr = make(map[string]int)
	for _, v := range a {
		arr[strings.ToUpper(string(v))]++
	}
	high := 0
	for _, v := range arr {
		if v > high {
			high = v
		}
	}
	answer := []string{}
	for i, v := range arr {
		if v == high {
			answer = append(answer, i)
		}
	}
	if len(answer) > 1 {
		fmt.Println("?")
	} else {
		fmt.Println(answer[0])
	}
}

func bj1157_copy() {
	var input string
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &input)

	var letters = make(map[uint8]int)
	for i := 0; i < 26; i++ {
		letters[uint8(i)+65] = 0
	}

	for i := 0; i < len(input); i++ {
		ascii := input[i]
		if ascii > 90 {
			ascii -= 32
		}
		letters[ascii]++
	}

	var maxVal = -1
	var maxKey string
	for key, val := range letters {
		if val > maxVal {
			maxVal = val
			maxKey = string(key)
		} else if val == maxVal {
			maxKey = "?"
		}
	}

	fmt.Println(maxKey)
}

func bj1152() {
	var input string
	reader := bufio.NewReader(os.Stdin)
	input, _ = reader.ReadString('\n')

	words := strings.Split(input, " ")
	var count int
	for i, _ := range words {
		if words[i] != "\n" && words[i] != "" {
			count++
		}
	}
	fmt.Println(count)
}

func bj1152_copy() {
	var input, k string
	reader := bufio.NewReader(os.Stdin)
	input, _ = reader.ReadString('\n')

	k = strings.Trim(strings.Trim(input, "\n"), " ")
	a := strings.Count(k, " ")
	if len(k) == 0 {
		fmt.Println(0)
	} else {
		fmt.Println(a + 1)
	}
}

func reverse(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func bj2908() {
	var a, b string
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &a, &b)
	num1, _ := strconv.Atoi(reverse(a))
	num2, _ := strconv.Atoi(reverse(b))

	if num1 > num2 {
		fmt.Println(num1)
	} else {
		fmt.Println(num2)
	}
}

func bj5622() {
	var a string
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &a)
	alpha := map[int]int{}
	for i := 65; i < 91; i++ {
		if i < 68 {
			alpha[i] = 3
		}
		if 67 < i && i < 71 {
			alpha[i] = 4
		}
		if 70 < i && i < 74 {
			alpha[i] = 5
		}
		if 73 < i && i < 77 {
			alpha[i] = 6
		}
		if 76 < i && i < 80 {
			alpha[i] = 7
		}
		if 79 < i && i < 84 {
			alpha[i] = 8
		}
		if 83 < i && i < 87 {
			alpha[i] = 9
		}
		if 86 < i && i < 91 {
			alpha[i] = 10
		}
	}
	var answer int
	for _, v := range a {
		answer += alpha[int(v)]
	}
	fmt.Println(answer)
}

func bj2941() {
	var input string
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &input)
	arr := map[string]bool{"c=": true, "c-": true, "dz=": true, "d-": true, "lj": true, "nj": true, "s=": true, "z=": true}
	var answer int
	for len(input) > 0 {
		if len(input) > 1 && arr[input[0:2]] {
			answer++
			input = input[2:]
		} else if len(input) > 2 && arr[input[0:3]] {
			answer++
			input = input[3:]
		} else {
			answer++
			input = input[1:]
		}
	}
	fmt.Println(answer)
}

func contain(arr []int, char int) bool {
	for _, v := range arr {
		if v == char {
			return true
		}
	}
	return false
}

func bj1316() {
	var num, answer int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &num)
	for i := 0; i < num; i++ {
		var (
			char  int
			words string
			arr   []int
		)
		value := true
		fmt.Fscanln(reader, &words)
		for _, v := range words {
			if char != int(v) && !contain(arr, int(v)) {
				arr = append(arr, int(v))
				char = int(v)
			} else if contain(arr, int(v)) && char != int(v) && arr[len(arr)-1] != int(v) {
				value = false
				break
			}
		}
		if value {
			answer++
		}
	}
	print(answer)
}

func main() {
	bj1316()
}
