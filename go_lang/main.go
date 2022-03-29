package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

/**
reader := bufio.NewReader(os.Stdin) 라인 하나 다받아오기
text, _ := reader.ReadString('\n')
split := strings.Fields(text)
*/

// @블랙잭 (브루트포스)!
func bj2798() {
	var n, m int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n, &m)
	text, _ := reader.ReadString('\n')
	split := strings.Fields(text)
	cards := []int{}

	for _, v := range split {
		card, _ := strconv.Atoi(v)
		cards = append(cards, card)
	}

	answer := 0
	for i := 0; i < len(cards)-2; i++ {
		for j := i + 1; j < len(cards)-1; j++ {
			for k := j + 1; k < len(cards); k++ {
				if (cards[i] + cards[j] + cards[k]) > m {
					continue
				} else {
					total := cards[i] + cards[j] + cards[k]
					if total <= m && answer < total {
						answer = total
					}
				}
			}
		}
	}
	fmt.Println(answer)
}
func bj2798_recursive() {
	add := func(arr []int) int {
		total := 0
		for _, v := range arr {
			total += v
		}
		return total
	}
	maxNum := func(arr []int) int {
		max := 0
		for _, v := range arr {
			if v > max {
				max = v
			}
		}
		return max
	}
	var n, m int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n, &m)
	text, _ := reader.ReadString('\n')
	split := strings.Fields(text)
	cards := []int{}
	visit := map[int]bool{}

	num := []int{}
	ansNum := []int{}

	for _, v := range split {
		card, _ := strconv.Atoi(v)
		cards = append(cards, card)
	}

	recur := func(dep, idx int) {}
	recur = func(dep, idx int) {
		if dep == 4 {
			total := add(num)
			if total <= m {
				ansNum = append(ansNum, total)
			}
		} else {
			for i := idx; i < n; i++ {
				if !visit[i] {
					visit[i] = true
					num = append(num, cards[i])
					recur(dep+1, i)
					visit[i] = false
					num = num[:len(num)-1]
				}
			}
		}
	}

	recur(1, 0)
	fmt.Println(maxNum(ansNum))
}

// @숫자 찾기 자릿수 더해서
func bj2231() {
	var n int
	getSplitSum := func(n int) (result int) {
		result = n
		for n != 0 {
			result += n % 10
			n /= 10
		}
		return
	}
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n)

	var creator int
	for i := 0; i < n; i++ {
		var splitSum = getSplitSum(i)
		if splitSum == n {
			creator = i
			break
		}
	}
	fmt.Println(creator)
}
func bj2231_ver2() {
	isGenerator := func(m, n int) bool {
		sum := m
		str := strconv.Itoa(m)
		fmt.Println(str)
		slice := strings.Split(str, "")
		for _, v := range slice {
			digit, _ := strconv.Atoi(v)
			sum += digit
		}
		if sum == n {
			return true
		}
		return false
	}

	scanner := bufio.NewScanner(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	scanner.Split(bufio.ScanWords)

	scanner.Scan()
	n, _ := strconv.Atoi(scanner.Text())

	for i := n - 100; i < n; i++ {
		if isGenerator(i, n) {
			fmt.Fprintln(writer, i)
			return
		}
	}
	fmt.Fprintln(writer, 0)
}

func bj2231_Coolway() {
	// 각자리수 갯수 *9
	gen := func(n int) (result int) {
		result = n
		for _, v := range fmt.Sprint(result) {
			result += int(v - 48)
		}
		return
	}
	gen(1)
	var target int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &target)

	startNum := target - 9*(len(fmt.Sprint(target)))
	answer := -1

	for i := startNum; i < target; i++ {
		if a := gen(i); a == target {
			answer = i
			break
		}
	}

	if answer < 0 {
		fmt.Println(0)
	} else {
		fmt.Println(answer)
	}
}

//@더치 찾기
type hw struct {
	w int
	h int
}

func bj7568() {
	var n int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n)
	rank := []hw{}
	score := make([]int, n)

	for i := 1; i < n+1; i++ {
		var weight, height int
		fmt.Fscanln(reader, &weight, &height)
		rank = append(rank, hw{weight, height})
	}
	for i := 0; i < len(rank); i++ {
		k := 0
		for j := 0; j < len(rank); j++ {
			if rank[i].h < rank[j].h && rank[i].w < rank[j].w {
				k += 1
			}
		}
		score[i] = k + 1
	}
	fmt.Println(strings.Trim(fmt.Sprint(score), "[]"))
}

func bj1018() {
	var n, m int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n, &m)
	var mat = make([]string, n)
	for i := 0; i < n; i++ {
		fmt.Fscanf(reader, "%v\n", &mat[i])
	}
	var min = n * m
	for i := 0; i < n-7; i++ {
		for j := 0; j < m-7; j++ {
			var cntChange1 = float64(0)
			var cntChange2 = float64(0)
			for k := i; k < i+8; k++ {
				for l := j; l < j+8; l++ {
					if (k+l)%2 == 0 {
						if string(mat[k][l]) == "B" {
							cntChange1++
						} else {
							cntChange2++
						}
					} else {
						if string(mat[k][l]) == "B" {
							cntChange2++
						} else {
							cntChange1++
						}
					}
				}
			}
			if min > int(math.Min(cntChange1, cntChange2)) {
				min = int(math.Min(cntChange1, cntChange2))
			}
		}
	}

	fmt.Println(min)
}

func bj1018_ver1() {
	reader := bufio.NewScanner(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	scanInt := func() int {
		reader.Scan()
		n, _ := strconv.Atoi(reader.Text())
		return n
	}

	getMin := func(slice [][]byte, a, b int) int {
		swB, swW := 0, 0
		for i := a; i < a+8; i++ {
			for j := b; j < b+8; j++ {
				if (i+j)%2 == 0 {
					if slice[i][j] == 'B' {
						swW++
					} else {
						swB++
					}
				} else {
					if slice[i][j] == 'B' {
						swB++
					} else {
						swW++
					}
				}
			}
		}
		if swB < swW {
			return swB
		}
		return swW
	}

	reader.Split(bufio.ScanWords)
	n, m := scanInt(), scanInt()
	board := make([][]byte, n)
	for i := 0; i < n; i++ {
		reader.Scan()
		board[i] = reader.Bytes()
	}
	min := 64

	for i := 0; i <= n-8; i++ {
		for j := 0; j <= m-8; j++ {
			tmp := getMin(board, i, j)
			if min > tmp {
				min = tmp
			}
		}
	}
	fmt.Fprintln(writer, min)
}

func bj1436() {
	var n int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n)
	numRange := []int{1, 19, 280, 3700, 10001}
	startRange := []int{666, 1666, 10666, 100666, 1000666}
	startNum := 0
	currentNum := 0
	count := 0
	for i := 1; i < len(numRange); i++ {
		if n > numRange[i-1] && n < numRange[i] {
			startNum = startRange[i]
			count = numRange[i-1]
		}
	}

	for {
		if count == n {
			break
		}
		if strings.Contains(fmt.Sprint(startNum), "666") {
			count++
			currentNum = startNum
			fmt.Println(currentNum, count)
		}
		startNum++
	}
	fmt.Println(currentNum)
}

func bj1436_ver1() {
	var n int
	count := 1
	title := 666

	r := bufio.NewReader(os.Stdin)
	w := bufio.NewWriter(os.Stdout)

	defer w.Flush()
	fmt.Fscan(r, &n)

	for i := 1666; count < n; i++ {
		temp := i
		for temp >= 666 {
			if temp%1000 == 666 {
				count++
				break
			}
			temp /= 10
		}
		title = i
	}
	fmt.Fprint(w, title)
}

func bj10974() {
	var n int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()
	fmt.Fscanln(reader, &n)

	visit := make([]bool, n+1)
	arr := []string{}
	recur := func(num int) {}
	recur = func(num int) {
		if num == n {
			fmt.Fprintln(writer, strings.Join(arr, " "))
			return
		}
		for i := 0; i < n; i++ {
			if !visit[i] {
				visit[i] = true
				str := strconv.Itoa(i + 1)
				arr = append(arr, str)
				recur(num + 1)
				visit[i] = false
				arr = arr[:len(arr)-1]
			}
		}
	}
	recur(0)
}

func main() {
	bj10974()
}
