package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

// @블랙잭 (브루트포스)!
func bj2798() {
	var n, m int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n, &m)
	text, _ := reader.ReadString('\n')
	split := strings.Fields(text)
	cards := []int{}

	for _, v := range split {
		card, _ := strconv.Atoi(v)
		cards = append(cards, card)
	}

	answer := 0
	for i := 0; i < len(cards)-2; i++ {
		for j := i + 1; j < len(cards)-1; j++ {
			for k := j + 1; k < len(cards); k++ {
				if (cards[i] + cards[j] + cards[k]) > m {
					continue
				} else {
					total := cards[i] + cards[j] + cards[k]
					if total <= m && answer < total {
						answer = total
					}
				}
			}
		}
	}
	fmt.Println(answer)
}

func main() {
	bj2798()
}
