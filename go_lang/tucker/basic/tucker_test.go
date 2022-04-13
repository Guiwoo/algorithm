package basic

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"sync"
	"testing"
	"time"
)

var wg sync.WaitGroup

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

func forLoopSum(arr *[]int) (result int) {
	result = 0
	for _, v := range *arr {
		result += v
	}
	return result
}

func goRoutine(arr *[]int) int {
	new := *arr
	wg.Add(4)

	firstHalf := new[:len(new)/4]
	secondHalf := new[len(new)/4 : len(new)/4*2]
	third := new[len(new)/4*2 : len(new)/4*3]
	fourth := new[len(new)/4*3:]
	total := 0

	go func() {
		new := forLoopSum(&firstHalf)
		total += new
		wg.Done()
	}()
	go func() {
		new := forLoopSum(&secondHalf)
		total += new
		wg.Done()
	}()
	go func() {
		new := forLoopSum(&third)
		total += new
		wg.Done()
	}()
	go func() {
		new := forLoopSum(&fourth)
		total += new
		wg.Done()
	}()

	wg.Wait()
	return total
}
func fill(arr []int) []int {
	for i, _ := range arr {
		arr[i] = i + 1
	}
	return arr
}

var arr = make([]int, 20000)

// func TestList(t *testing.T) {
// 	const expected = 500000500000
// 	for i, _ := range arr {
// 		arr[i] = i + 1
// 	}
// 	t.Run("For loop", func(t *testing.T) {
// 		startTime := time.Now()
// 		answer := forLoopSum(&arr)
// 		elapsed := time.Since(startTime)

// 		if answer != expected {
// 			t.Errorf("Want :%d,Got: %d", expected, answer)
// 		}
// 		fmt.Printf("%d\n", elapsed)
// 	})

// 	t.Run("Go Routine", func(t *testing.T) {
// 		startTime := time.Now()
// 		answer := goRoutine(&arr)
// 		elapsed := time.Since(startTime)

// 		if answer != expected {
// 			t.Errorf("Want :%d,Got: %d", expected, answer)
// 		}
// 		fmt.Printf("%d\n", elapsed)
// 	})

// }

var arr2 = fill(arr)

func Benchmark_Sum(b *testing.B) {
	for i := 0; i < b.N; i++ {
		forLoopSum(&arr2)
	}
}

func Benchmark_Sum2(b *testing.B) {
	for i := 0; i < b.N; i++ {
		goRoutine(&arr2)
	}
}
