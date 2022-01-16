package main

import "fmt"

func num1() {
	//백준 2739
	for i := 1; i < 10; i++ {
		fmt.Printf("%d * %d = %d\n", 2, i, 2*i)
	}
}

func num2() {
	//백준 8393
	var n int
	_, err := fmt.Scan(&n)

	if err == nil {

		fmt.Println(n * (n + 1) / 2)

	}
}

// var a, b int
// fmt.Scanf("%d %d", &a, &b)
// fmt.Printf("%d\n", a+b)

func main() {
	var n string
	for i := 0; i < 5; i++ {
		n = fmt.Sprint(n, i+1, "\n")
	}
	fmt.Println(n)
}
