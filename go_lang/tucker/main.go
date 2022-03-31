package tucker

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"sync"
	"time"
)

/**
1. a number for random generate
2. get a number value from input
3. evolve lower or greater
4. if it's equal break
**/

func GuessNum100() {
	var cnt int
	rand.Seed(time.Now().UnixNano())
	reader := bufio.NewReader(os.Stdin)

	checker := func(num *int, target *int) {
		if *num > *target {
			fmt.Println("지정 숫자 보다 큽니다")
		} else {
			fmt.Println("지정 숫자 보다 작습니다")
		}
	}

	target := rand.Intn(100) + 1

	fmt.Println("숫자가 정해졌습니다, 숫자를 입력해주세요(1~100):")
	for {
		var n int
		_, err := fmt.Fscanln(reader, &n)
		if err != nil {
			reader.ReadString('\n')
			fmt.Println("숫자를 입력해주세요")
			continue
		}
		if n == target {
			fmt.Println("정답입니다. 총 진행한 횟수", cnt)
			return
		} else {
			checker(&n, &target)
			cnt++
		}
	}
}

func square(wg *sync.WaitGroup, ch chan int) {
	n := <-ch
	time.Sleep(time.Second)
	fmt.Println("Square: ", n*n)
	wg.Done()
}

func ChannelEx() {
	var wg sync.WaitGroup
	ch := make(chan int)
	wg.Add(1)
	go square(&wg, ch)
	ch <- 9
	wg.Wait()
}
