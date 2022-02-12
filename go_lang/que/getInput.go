package que

import (
	"bufio"
	"fmt"
	"os"
)

func getInput() {
	stdin := bufio.NewReader(os.Stdin)
	for {
		fmt.Println("입력하세요")
		var number int
		_, err := fmt.Scanln(&number)
		if err != nil {
			fmt.Println("숫자로 입력하세요")
			//키보드 버퍼를 지우자.
			stdin.ReadString('\n')
			continue
		}
		fmt.Printf("입력하신 문자는 %d \n", number)
		if number%2 == 0 {
			break
		}
	}
	fmt.Println("For 문 이 종료되었습니다.")
}
