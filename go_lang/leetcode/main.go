package main

import (
	"fmt"
	"math"
	"reflect"
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

//개무식하다 진짜 ㅅㅂ 2시간을 쳐푸네 ㅂㅅ
//@8번
func atoi() {
	// s := "+1"
	s := "abcc123dsvnkld"
	//중간에 +- 가 들어가면 쳐내
	var (
		filtered   = []rune{}
		isMinus    = false
		zeroAscii  = 48
		nineAscii  = 57
		minusAscii = 45
		plusAscii  = 43
		count      = 0
		result     = 0
	)
	s = strings.Trim(s, " ")
	for _, v := range s {
		if int(v) == minusAscii || int(v) == plusAscii {
			if len(filtered) > 0 {
				fmt.Println("fuck")
				break
			}
		}
		if zeroAscii <= int(v) && int(v) <= nineAscii || int(v) == minusAscii || int(v) == plusAscii {
			filtered = append(filtered, v)
		} else {
			break
		}
	}
	for i := 0; i < len(filtered); i++ {
		if zeroAscii <= int(filtered[i]) && int(filtered[i]) <= nineAscii {
			if int(filtered[i]) != zeroAscii && len(filtered)-i-1 > 10 {
				if int(filtered[0]) == minusAscii {
					isMinus = true
				}
				result = 1 << 31
				break
			}
			result += int(filtered[i]-48) * int(math.Pow(float64(10), float64(len(filtered)-i-1)))
		} else if int(filtered[i]) == minusAscii {
			isMinus = true
			count++
		} else {
			count++
		}

		if count == 2 {
			result = 0
			break
		}

	}
	if isMinus {
		result *= -1
	}
	fmt.Println(result)
	if result <= -(1<<31) || result >= (1<<31)-1 {
		if result > 0 {
			fmt.Println(result - 1)
			return
		} else {
			fmt.Println(-(1 << 31))
			return
		}
	}
	fmt.Println(result)
}
func atoi_2(str string) int {
	var ans int64 = 0
	var sign int64 = 1
	start := false

	for _, b := range str {
		fmt.Println(ans, sign)
		if b <= '9' && b >= '0' {
			if !start {
				start = true
			}
			ans = ans*10 + int64(b) - int64('0')
			if ans*sign > math.MaxInt32 {
				return math.MaxInt32
			} else if ans*sign < math.MinInt32 {
				return math.MinInt32
			}
		} else if !start && b == '+' {
			start = true
		} else if !start && b == '-' {
			start = true
			sign = -1
		} else if !start && b == ' ' {
			continue
		} else {
			break
		}
	}
	return int(ans * sign)
}
func whatIwant_myAtoi(s string) int {
	var (
		isMinus  = 1
		str      = strings.TrimSpace(s)
		result   = 0
		maxValue = (1 << 31) - 1
	)

	if str == "" {
		return 0
	}

	if str[0] == '-' {
		isMinus = -1
		str = str[1:]
	} else if str[0] == '+' {
		str = str[1:]
	} else if str[0] < '0' || str[0] > '9' {
		return 0
	}

	for _, v := range str {
		if v >= '0' && v <= '9' {
			result = 10*result + int(v) - '0'
			if result > maxValue {
				return ((maxValue)*isMinus + (isMinus-1)/2)
			}
		} else {
			break
		}
	}
	return (result * isMinus)
}

//@9번
func isPalindrome(x int) bool {
	textX := fmt.Sprint(x)
	answer := true

	for i := 0; i < len(textX)/2; i++ {
		if textX[i] != textX[len(textX)-1-i] {
			answer = false
			break
		}
	}
	return answer
}
func isPalindrome_ver2(x int) bool {
	y := x
	if x < 0 {
		return false
	}
	result := 0
	for y > 0 {
		result = (result * 10) + (y % 10)
		y /= 10
	}
	return result == y
}

//@10번
// func isMatch(s,p string) bool {
// 	sLen := len(s)
// 	pLen := len(p)
// 	dp := make([][]bool,sLen+1)
// 	dp[0][0] = true
// }

func isMatch(s string, p string) bool {
	n1 := len(s)
	n2 := len(p)
	dp := make([][]bool, n1+1)

	for i := 0; i <= n1; i++ {
		dp[i] = make([]bool, n2+1)
	}

	dp[0][0] = true
	for i := 2; i <= n2; i += 2 {
		if p[i-1] == '*' {
			dp[0][i] = true
		} else {
			break
		}
	}

	for i := 1; i <= n1; i++ {
		for j := 1; j <= n2; j++ {
			if s[i-1] == p[j-1] || p[j-1] == '.' {
				dp[i][j] = dp[i-1][j-1]
				continue
			}

			if p[j-1] == '*' {
				dp[i][j] = dp[i][j-1] ||
					dp[i][j-2] ||
					(dp[i-1][j] && (p[j-2] == s[i-1] || p[j-2] == '.'))
				continue
			}
		}
	}

	return dp[n1][n2]
}

//이거는 러닝타임 초과
func maxArea1(h []int) int {
	min := func(a, b int) int {
		if a < b {
			return a
		}
		return b
	}
	max := func(a, b int) int {
		if a > b {
			return a
		}
		return b
	}

	n := len(h)
	var result int
	for left := 0; left < n-1; left++ {
		for right := left + 1; right < n; right++ {
			currArea := min(h[left], h[right]) * (right - left)
			result = max(result, currArea)
		}
	}
	return result
}

type Area struct {
	Area int
	L    int
	R    int
}

func maxArea(h []int) int {
	a := new(Area)
	a.R = len(h) - 1

	for a.L < a.R {
		if h[a.L] < h[a.R] {
			a.setArea(h[a.L])
			a.L++
		} else {
			a.setArea(h[a.R])
			a.R--
		}
	}
	return a.Area
}

func (a *Area) setArea(h int) {
	if a.Area < h*(a.R-a.L) {
		a.Area = h * (a.R - a.L)
	}
}

// 다 비슷하게 한듯 ?
func intToRoman(num int) string {
	// 	1000 부터 나누기 시작하면 될듯 ?
	hash := map[int]string{1000: "M", 900: "CM", 500: "D", 400: "CD", 100: "C", 90: "XC", 50: "L", 40: "XL", 10: "X", 9: "IX", 5: "V", 4: "IV", 1: "I"}
	sets := []int{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
	current := 0
	result := ""

	for {
		if num == 0 {
			break
		}

		result += strings.Repeat(hash[sets[current]], num/sets[current])
		num = num % sets[current]
		current++
	}

	return result
}

func romanToInt(s string) int {
	hash := map[string]int{"M": 1000, "CM": 900, "D": 500, "CD": 400, "C": 100, "XC": 90, "L": 50, "XL": 40, "X": 10, "IX": 9, "V": 5, "IV": 4, "I": 1}
	current := 0
	result := 0
	for {
		if current >= len(s) {
			break
		}
		if current+2 <= len(s) {
			target := s[current : current+2]
			if val, ok := hash[target]; ok {
				current += 2
				result += val
			} else {
				result += hash[string(s[current])]
				current++
			}
		} else {
			result += hash[string(s[current])]
			current++
		}
	}
	return result
}

func romanToInt_ver2(s string) int {
	type Value struct {
		Value  int
		Before byte
	}
	dic := map[byte]Value{
		'I': {1, ' '}, 'V': {5, 'I'}, 'X': {10, 'I'}, 'L': {50, 'X'}, 'C': {100, 'X'}, 'D': {500, 'C'}, 'M': {1000, 'C'},
	}

	sum := int(0)
	before := ' '
	length := len(s)

	for i := 0; i < length; i++ {
		current := s[i]
		d := dic[current]
		sum += d.Value
		fmt.Println("before ", sum)
		if before == rune(d.Before) {
			sum -= dic[d.Before].Value * 2
		}
		fmt.Println(sum)
		before = rune(current)
	}

	return sum
}

// cool 0ms/100% 2.6mb
// slice byte way faster than normal strings added
func longestCommonPrefix(strs []string) string {
	getMinLength := func(s1, s2 string) int {
		if len(s1) > len(s2) {
			return len(s2)
		}
		return len(s1)
	}
	result := strs[0]
	for i := 1; i < len(strs); i++ {
		currentAnswer := []byte{}
		minLoop := getMinLength(result, strs[i])
		for j := 0; j < minLoop; j++ {
			if result[j] == strs[i][j] {
				currentAnswer = append(currentAnswer, result[j])
			} else {
				break
			}
		}
		result = string(currentAnswer)
		if result == "" {
			break
		}
	}
	return result
}

func threeSum(nums []int) [][]int {
	if len(nums) < 3 {
		return [][]int{}
	}
	duplciate := func(answers [][]int, target []int) bool {
		for _, v := range answers {
			if ok := reflect.DeepEqual(v, target); ok {
				return true
			}
		}
		return false
	}
	hashed := map[int]bool{}
	for _, v := range nums {
		hashed[v] = true
	}
	sort.Ints(nums)
	answer := [][]int{}
	current := 1

	for i := 0; i < len(nums)-1; i++ {
		target := (nums[i] + nums[current]) * -1
		if hashed[target] {
			theAnswer := []int{nums[i], nums[current], target}
			sort.Ints(theAnswer)
			//duplicate check and add
			ok := duplciate(answer, theAnswer)
			if !ok {
				answer = append(answer, theAnswer)
			}
		}
		current++
	}

	return answer
}

func threeSum_ver2(nums []int) [][]int {
	res := [][]int{}
	sort.Ints(nums)
	for i := 0; i < len(nums); i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		} // 중복체크 이렇게 와씨
		left, right := i+1, len(nums)-1
		for left < right {
			sum := nums[i] + nums[left] + nums[right]
			if sum == 0 {
				res = append(res, []int{nums[i], nums[left], nums[right]})
				for left < right && nums[left] == nums[left+1] {
					left++
				}
				for left < right && nums[right-1] == nums[right] {
					right--
				}
				left++
				right--
			} else if sum > 0 {
				right--
			} else {
				left++
			}
		}
	}

	return res
}

func threeSum_ver4(nums []int) [][]int {
	answer := [][]int{}
	sort.Ints(nums)

	for i, num := range nums {
		if i > 0 && nums[i-1] == nums[i] {
			continue
		}
		target, j, k := -num, i+1, len(nums)-1
		for j < k {
			if nums[j]+nums[k] == target {
				answer = append(answer, []int{num, nums[j], nums[k]})
				for j < k && nums[j] == nums[j+1] {
					j++
				}
				for j < k && nums[k] == nums[k-1] {
					k--
				}
				j++
				k--
			} else if nums[j]+nums[k] > target {
				k--
			} else {
				j++
			}
		}
	}
	return answer
}

func threeSumClosest(nums []int, target int) int {
	getAbs := func(a int) int {
		if a < 0 {
			return a * -1
		}
		return a
	}

	answer := 0
	if len(nums) == 3 {
		answer := nums[0] + nums[1] + nums[2]
		return answer
	}
	sort.Ints(nums)
	left, right := 0, len(nums)-1
	for right-left > 2 {
		gapLeft := getAbs(nums[left] - target)
		gapRight := getAbs(nums[right] - target)
		if gapRight > gapLeft {
			right--
		} else if gapLeft > gapRight {
			left++
		} else {
			for right-left > 2 && gapLeft <= getAbs(nums[right]-target) {
				left++
			}
			for right-left > 2 && gapRight <= getAbs(nums[left]-target) {
				right--
			}
		}
	}
	fmt.Println(left, right)
	for _, v := range nums[left : right+1] {
		answer += v
	}
	return answer
}

func threeSumClosest_ver2(nums []int, target int) int {

	abs := func(a int) int {
		if a < 0 {
			return -a
		}
		return a
	}

	sort.Ints(nums)
	ans := nums[0] + nums[1] + nums[2]
	n := len(nums)

	for i := 0; i < n; i++ {
		j, k := i+1, n-1
		for j < k {
			v := nums[i] + nums[j] + nums[k]
			if abs(target-v) < abs(target-ans) {
				ans = v
			}
			if v <= target {
				j++
			} else {
				k--
			}
		}
	}
	return ans
}

func main() {
	nums := []int{0, 2, 1, -3}
	answer := threeSumClosest(nums, 1)
	fmt.Println(answer)
}
