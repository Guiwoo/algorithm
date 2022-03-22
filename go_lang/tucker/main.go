package tucker

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"time"
)

/**
1. a number for random generate
2. get a number value from input
3. evolve lower or greater
4. if it's equal break
**/

func GuessNum100() {
	rand.Seed(time.Now().UnixNano())
	reader := bufio.NewReader(os.Stdin)

	checker := func(num *int, target *int) {
		if *num > *target {
			fmt.Println("큽니다")
		} else {
			fmt.Println("작습니다")
		}
	}
	target := rand.Intn(99) + 1
	fmt.Println("숫자가 정해졌습니다, 숫자를 입력해주세요")

	for {
		var n int
		fmt.Fscanln(reader, &n)
		if n == target {
			fmt.Println("정답입니다.")
			return
		} else {
			checker(&n, &target)
		}
	}
}
