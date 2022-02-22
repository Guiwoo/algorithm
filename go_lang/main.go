package main

import (
	"fmt"
)

func main() {
	var (
		first  int
		second int
		third  int
	)

	fmt.Scanf("%d %d %d", &first, &second, &third)

	if first == second && second == third {
		fmt.Println(10000 + (first)*1000)
	} else if first == second || second == third || third == first {
		value := 0
		if first == second {
			value = first
		} else if second == third {
			value = second
		} else {
			value = third
		}
		fmt.Println(1000 + (value)*100)
	} else {
		value := first
		if value < second {
			value = second
		}
		if value < third {
			value = third
		}
		fmt.Println(value * 100)
	}
}
