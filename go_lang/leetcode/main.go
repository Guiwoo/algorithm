package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

// func searchNum(nums []int, target int) (int, bool) {
// 	for i, v := range nums {
// 		if v == target {
// 			return i, true
// 		}
// 	}
// 	return 0, false
// }

// func twoSum(nums []int, target int) []int {
// 	answer := []int{}
// 	if len(nums) == 2 {
// 		return []int{0, 1}
// 	}
// 	for i, v := range nums {
// 		// v 를 뺴버려 target에서 그리고target 값이 있으면 ? 끝내 없으면 ? 컨티뉴 ?낫베ㅡㄷ ?
// 		realTarget := target - v
// 		new := []int{}

// 		new = append(new, nums[0:i]...)
// 		new = append(new, nums[i+1:]...)

// 		index, found := searchNum(new, realTarget)
// 		if found {
// 			answer = append(answer, i)
// 			answer = append(answer, index+1)
// 			break
// 		}
// 	}
// 	return answer
// }

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

func main() {
	var input string
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &input)
	a := lengthOfLongestSubstring2(input)
	fmt.Println(a)
}
