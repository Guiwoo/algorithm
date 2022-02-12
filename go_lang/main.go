package main

import (
	"fmt"
	"strings"
)

func main() {
	num := 5
	for i := 1; i <= num; i++ {
		str1 := strings.Repeat(" ", num-i)
		str2 := strings.Repeat("*", (i*2)-1)
		fmt.Println(str1 + str2 + str1)
	}
}
