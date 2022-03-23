package main

import (
	"errors"
	"testing"
)

func TestCarpet(t *testing.T) {
	insertNuminArrBySlicing := func(arr []int, index int, insert []int) ([]int, error) {
		if index > len(arr) {
			return nil, errors.New("index is out of range")
		}
		for i := 0; i < len(insert); i++ {
			arr = append(arr, insert[i])
		}
		copy(arr[index+len(insert):], arr[index:])
		for i := 0; i < len(insert); i++ {
			arr[index+i] = insert[i]
		}
		return arr, nil
	}
	arr := []int{1, 2, 3, 4, 5, 6, 7, 8, 9}
	insert := []int{10000, 2000}

	got, _ := insertNuminArrBySlicing(arr, 5, insert)
	want := []int{1, 2, 3, 4, 5, 10000, 2000, 6, 7, 8, 9}

	for i := range got {
		if got[i] != want[i] {
			t.Errorf("Want %q,but Got %q", want[i], got[i])
		}
	}
}
