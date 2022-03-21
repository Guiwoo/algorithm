package main

import (
	"bufio"
	"fmt"
	"os"
)

func bj10872() {
	var a int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &a)

	total := 1
	var fct = func(n int) {}
	fct = func(n int) {
		if n < 1 {
			return
		}
		total *= n
		fct(n - 1)
	}
	fct(a)
	fmt.Println(total)
}

func main() {
	bj10872()
}
