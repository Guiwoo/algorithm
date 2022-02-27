package main

import (
	"fmt"
	"testing"
	"time"
)

func bj15596_2(arr []int) int {
	answer := 0
	for _, v := range arr {
		answer += v
	}
	return answer
}

func sumAlt(arr []int, c chan int) {
	sum := 0
	for _, v := range arr {
		sum += v
	}
	c <- sum
}

func bj15596_3(arr []int) int {
	c := make(chan int)
	go sumAlt(arr[:len(arr)/2], c)
	go sumAlt(arr[len(arr)/2:], c)
	x, y := <-c, <-c
	return x + y
}

func Test_bj15596(t *testing.T) {
	arr := make([]int, 1000)
	expected := 500500
	for i, _ := range arr {
		arr[i] = i + 1
	}
	t.Run("Kind of Reducer", func(t *testing.T) {
		startTime := time.Now()
		answer := Bj15596(arr)
		elapsed := time.Since(startTime)
		if answer != expected {
			t.Errorf("Want :%d, Got :%d", expected, answer)
		}
		fmt.Printf("%d\n", elapsed)
	})
	t.Run("Typical way", func(t *testing.T) {
		startTime := time.Now()
		answer := bj15596_2(arr)
		elapsed := time.Since(startTime)

		if answer != expected {
			t.Errorf("Want :%d, Got :%d", expected, answer)
		}
		fmt.Printf("%d\n", elapsed)
	})
	t.Run("Go Rutine", func(t *testing.T) {
		startTime := time.Now()
		answer := bj15596_3(arr)
		elapsed := time.Since(startTime)
		if answer != expected {
			t.Errorf("Want :%d, Got :%d", expected, answer)
		}
		fmt.Printf("%d\n", elapsed)
	})
}
