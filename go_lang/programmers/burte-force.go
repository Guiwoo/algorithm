package programmers

import (
	"fmt"
	"strconv"
	"strings"
)

// 음 주어진 숫자만 다시 돌려서 내보낼때 갯수안줄이구
func genP(sampleRune []rune, left, right int) {
	if left == right {
		fmt.Println(string(sampleRune))
	} else {
		for i := left; i <= right; i++ {
			sampleRune[left], sampleRune[i] = sampleRune[i], sampleRune[left]
			genP(sampleRune, left+1, right)
			sampleRune[left], sampleRune[i] = sampleRune[i], sampleRune[left]
		}
	}
}

func getAprimeFromNumbers(a string) int {
	// 숫자 들을 문자열로 받아서 숫자들 의 조합이 소수인 경우를 반환
	// var a string
	// reader := bufio.NewReader(os.Stdin)
	// writer := bufio.NewWriter(os.Stdout)
	// writer.Flush()
	// fmt.Fscanln(reader, &a)

	var recur = func(num int, nums int) {}
	rs := []string{}
	rsChecker := map[int]bool{}
	visit := make(map[int]bool)
	count := 0

	isPrime := func(num int) {
		rsChecker[num] = true
		if num == 0 || num == 1 {
			return
		}
		for i := 2; i*i <= num; i++ {
			if num%i == 0 {
				return
			}
		}
		count++
	}

	primeChecker := func(str []string) {
		arrString := strings.Join(str, "")
		number, _ := strconv.Atoi(arrString)
		if !rsChecker[number] {
			isPrime(number)
		}
	}

	recur = func(num int, length int) {
		if num == length {
			primeChecker(rs)
			return
		}
		for i := 0; i < len(a); i++ {
			if !visit[i] {
				visit[i] = true
				rs = append(rs, string(a[i]))
				recur(num+1, length)
				visit[i] = false
				rs = rs[0 : len(rs)-1]
			}
		}
	}

	for j := 1; j <= len(a); j++ {
		recur(0, j)
	}

	// fmt.Fprintln(writer,count)
	return count
}

func carpet(brown, yellow int) []int {
	var x, y int
	y = 3
	x = (brown-(y*2))/2 + 2

	for y >= 3 {
		if (x-2)*(y-2) == yellow {
			break
		}
		x -= 1
		y += 1
	}
	return []int{x, y}
}
