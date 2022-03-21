package main

import "testing"

func TestCarpet(t *testing.T) {
	got := carpet(24, 24)
	want := []int{8, 6}

	for i, _ := range want {
		if want[i] != got[i] {
			t.Errorf("got %q, want %q", got, want)
		}
	}
}
