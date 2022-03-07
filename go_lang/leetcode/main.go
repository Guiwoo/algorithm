package main

import "fmt"

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

func main() {
	var arr = []int{3, 3}
	answer := twoSum(arr, 9)
	fmt.Println(answer)
}
