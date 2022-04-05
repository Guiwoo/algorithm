package main

import (
	"fmt"
	"math"
	"sort"
	"strconv"
	"strings"
)

func searchNum(nums []int, target int) (int, bool) {
	for i, v := range nums {
		if v == target {
			return i, true
		}
	}
	return 0, false
}

func twoSum_ver2(nums []int, target int) []int {
	answer := []int{}
	if len(nums) == 2 {
		return []int{0, 1}
	}
	for i, v := range nums {
		// v 를 뺴버려 target에서 그리고target 값이 있으면 ? 끝내 없으면 ? 컨티뉴 ?낫베ㅡㄷ ?
		realTarget := target - v
		new := []int{}

		new = append(new, nums[0:i]...)
		new = append(new, nums[i+1:]...)

		index, found := searchNum(new, realTarget)
		if found {
			answer = append(answer, i)
			answer = append(answer, index+1)
			break
		}
	}
	return answer
}

func twoSum(nums []int, target int) []int {
	m := make(map[int]int)
	for idx, num := range nums {
		if v, found := m[target-num]; found {
			return []int{v, idx}
		}
		m[num] = idx
	}
	return nil
}

func arrChecker(arr []int, target int) (bool, int) {
	checker := make(map[int]bool)
	index := make(map[int]int)
	for i, v := range arr {
		checker[int(v)] = true
		index[int(v)] = i
	}
	return checker[target], index[target]
}

func lengthOfLongestSubstring(s string) int {

	arr := []int{}
	count := 0

	for _, v := range s {
		exsit, index := arrChecker(arr, int(v))
		if !exsit {
			arr = append(arr, int(v))
		}
		if exsit && index > -1 {
			if len(arr) > count {
				count = len(arr)
			}
			arr = arr[index+1:]
			arr = append(arr, int(v))
		}
	}
	if len(arr) > count {
		return len(arr)
	}
	fmt.Println(arr)
	return count
}

func lengthOfLongestSubstring2(s string) int {
	var highestCount, distance, prevPos int
	prevPos = -1
	for i, v := range s {
		c := string(v)
		pos := strings.LastIndex(s[:i], c)
		fmt.Println(s[:i])
		if pos > prevPos {
			fmt.Println("running", c, pos, prevPos)
			prevPos = pos
		}
		distance = i - prevPos
		if distance > highestCount {
			highestCount = distance
		}
		fmt.Printf("Leeter: %s\nPosition: %d\nPrevPos: %d\nCurrent Index: %d\n", string(v), pos, prevPos, i)
		fmt.Printf("Distance: %d\n\n", distance)
	}
	return highestCount
}

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	a := append(nums1, nums2...)

	if len(a) < 1 {
		return float64(0)
	}

	for i := 1; i < len(a); i++ {
		for j := i; j > 0; j-- {
			if a[j] < a[j-1] {
				a[j], a[j-1] = a[j-1], a[j]
			} else {
				break
			}
		}
	}

	answer := 0

	if len(a)%2 == 0 {
		answer = a[len(a)/2] + a[len(a)/2-1]
	} else {
		answer = a[len(a)/2] * 2
	}

	return float64(answer) / 2
}

func find_ver2(nums1 []int, nums2 []int) float64 {
	var a []int
	a = append(a, nums1...)
	a = append(a, nums2...)
	sort.Ints(a)

	l := len(a)
	var med float64
	if l%2 == 1 {
		idx := (l) / 2
		med = float64(a[idx])
	} else {
		idx1 := l/2 - 1
		idx2 := l / 2
		med = float64(a[idx1]+a[idx2]) / 2
	}
	return med
}

func longestPalindrome(s string) string {
	checker := func(s string) bool {
		l := len(s)
		for i := 0; i < l; i++ {
			if s[i] != s[l-i-1] {
				return false
			}
		}
		return true
	}
	answer := ""
	for i := 0; i < len(s); i++ {
		for j := i + 1; j < len(s)+1; j++ {
			if checker(s[i:j]) {
				if len(answer) < len(s[i:j]) {
					answer = s[i:j]
				}
			}
		}
	}
	return answer
}

func Palindrome_ver2(s string) string {
	chars := []rune(s)
	l := len(chars)
	start := 0
	end := 0

	checkPalindrome := func(i, j int) {
		for i > -1 && j < l && chars[i] == chars[j] {
			if j-i > end-start {
				start = i
				end = j
			}
			i--
			j++
		}
	}
	for center := 0; center < l; center++ {
		checkPalindrome(center, center)
		checkPalindrome(center, center+1)
	}
	return s[start : end+1]
}

func zigzag_myanswer(numRows int, s string) string {
	// s := "PAYPALISHIRING"
	// numRows := 3
	if numRows < 2 {
		return s
	}
	var arr = make([]*[]rune, numRows)
	for i, _ := range arr {
		arr[i] = &[]rune{}
	}
	current := 0
	goDown := false
	for i, v := range s {
		*arr[current] = append(*arr[current], v)
		if i%(numRows-1) == 0 {
			goDown = !goDown
		}
		if goDown {
			current++
		} else {
			current--
		}
	}

	var answer = ""
	for _, v := range arr {
		a := *v
		answer += string(a)
	}
	return string(answer)
}

func zigzag_ver2() {
	var numRows = 3
	s := "PAYPALISHIRING"
	result := make([][]byte, numRows)

	goDown := true
	traverseIdx := 0

	for i := 0; i < len(s); i++ {
		result[traverseIdx] = append(result[traverseIdx], s[i])
		if traverseIdx == len(result)-1 {
			goDown = false
		}
		if traverseIdx == 0 {
			goDown = true
		}

		if goDown {
			traverseIdx++
		} else {
			traverseIdx--
		}
	}

	output := ""
	for _, bytes := range result {
		output += string(bytes)
	}
	fmt.Println(output)
}
func zigzag_ver3_from_python() {
	var numRows = 1
	var arr = make([][]rune, numRows)
	s := "A"
	if numRows == 0 || numRows == 1 {
		fmt.Println(s)
		return
	}
	current := 0
	for i, v := range s {
		arr[current] = append(arr[current], v)
		if (i/(numRows-1))%2 == 0 {
			current++
		} else {
			current--
		}
	}
	result := ""
	for _, v := range arr {
		result += string(v[:])
	}
	fmt.Println(result)
}

//와 처음 100 퍼센트 받았다 신난다 post 도 쓰고
// https://leetcode.com/problems/reverse-integer/discuss/1917339/Go-Solving
func reverse(x int) (answer int) {
	b := ""
	numRange := (1 << 31)
	if x < 0 {
		b += "-"
		x = int(math.Abs(float64(x)))
	}
	for x > 0 {
		b += fmt.Sprint(x % 10)
		x /= 10
	}
	answer, _ = strconv.Atoi(b)
	if answer > numRange || answer < -numRange {
		return 0
	}
	return answer
}

func main() {

}
