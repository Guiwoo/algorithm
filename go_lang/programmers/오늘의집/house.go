package main

import "fmt"

func splitPath(path string) []string {
	splitPath := []string{}
	str := ""
	for i := 0; i < len(path)-1; i++ {
		str += string(path[i])
		if path[i] != path[i+1] {
			splitPath = append(splitPath, str)
			str = ""
		}
	}
	str += string(path[len(path)-1])
	if len(str) > 0 {
		splitPath = append(splitPath, str)
	}
	return splitPath
}
func speak(time int, distance int, way string, turn string) string {
	turn = trunWay(way, turn)
	fmt.Printf("Time %d: Go straight %dm and turn %s\n", time, distance, turn)
	return fmt.Sprintf("Time %d: Go straight %dm and turn %s\n", time, distance, turn)
}
func trunWay(current, turn string) (result string) {
	switch {
	case current == "E":
		if turn == "N" {
			result = "left"
		} else {
			result = "right"
		}
	case current == "S":
		if turn == "E" {
			result = "left"
		} else {
			result = "right"
		}
	case current == "N":
		if turn == "E" {
			result = "right"
		} else {
			result = "left"
		}
	case current == "W":
		if turn == "N" {
			result = "right"
		} else {
			result = "left"
		}
	}
	return result
}
func codingtest() {
	var (
		path   = "EEESEEEEEENNNN"
		result = []string{}
	)
	time := 0
	splitPath := splitPath(path)

	for i := 0; i < len(splitPath)-1; i++ {
		turn := string(splitPath[i+1][0])
		if len(splitPath[i]) > 4 {
			current := 0
			letter := string(splitPath[i][0])
			for len(splitPath[i]) > current {
				if len(splitPath[i]) == 5 {
					result = append(result, speak(time, 500, letter, turn))
					time += 5
					break
				} else if len(splitPath[i])%5 == 0 {
					result = append(result, speak(time, 500, letter, turn))
					time += 5
					splitPath[i] = splitPath[i][5:]
				} else {
					time += (len(splitPath[i]) % 5)
					splitPath[i] = splitPath[i][len(splitPath[i])%5:]
				}
				current++
			}
		} else {
			result = append(result, speak(time, len(splitPath[i])*100, string(splitPath[i][0]), turn))
			time += len(splitPath[i])
		}
	}
	fmt.Println(result)
}

func byteToSum(s string) (result int) {
	byteV := []byte(s)
	result = 0
	for _, value := range byteV {
		result += int(value)
	}
	return result
}

func theChecker(arr map[string]string, count int) map[string]string {
	countDepth := count
	for i, v := range arr {
		if countDepth > len(arr) {
			break
		}
		if v[0] == '{' {
			target := v[1 : len(v)-1]
			if target == arr[i] || i == target {
				countDepth++
				break
			} else {
				arr[i] = arr[target]
				arr = theChecker(arr, countDepth)
			}
		}
	}
	return arr
}

func main() {
	// tstring := "this is {template} {template} is {state}"
	variables := [][]string{
		{"b", "{c}"},
		{"a", "{b}"},
		{"e", "{f}"},
		{"h", "i"},
		{"d", "{e}"},
		{"f", "{d}"},
		{"c", "d"},
	}

	checker := make(map[string]string, len(variables))

	for _, v := range variables {
		checker[v[0]] = v[1]
	}

	// splitTstring := strings.Split(tstring, " ")
	fmt.Println(checker)
	checker = theChecker(checker, 0)
	fmt.Println(checker)

	// for i, v := range splitTstring {
	// 	if v[0] == '{' && v[len(v)-1] == '}' {
	// 		splitTstring[i] = checker[v[1:len(v)-1]]
	// 	}
	// }

}
