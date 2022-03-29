package tucker

import (
	"errors"
	"fmt"
)

type QueueBySlice struct {
	Data []interface{}
}

func (q *QueueBySlice) New(a ...interface{}) []interface{} {
	gotValue := a
	arr := []interface{}{}
	arr = append(arr, gotValue...)
	return arr
}

func (q *QueueBySlice) Push(a interface{}) {
	q.Data = append(q.Data, a)
}

func (q *QueueBySlice) Pop() (interface{}, error) {
	if len(q.Data) == 0 {
		return 0, errors.New("cant pop out of index")
	}
	poped := q.Data[0]
	q.Data = q.Data[1:]
	return poped, nil
}

func QueueTest() {
	queue := QueueBySlice{}
	queue.New()
	for i := 1; i < 6; i++ {
		queue.Push(i)
	}
	for _, v := range queue.Data {
		fmt.Printf("%v=>", v)
	}
	for i := 1; i < 6; i++ {
		n, err := queue.Pop()
		if err != nil || n == 0 {
			fmt.Errorf("%v", err)
			break
		}
		fmt.Printf("index %d ,%v is removed\n", i, n)
		for _, v := range queue.Data {
			fmt.Printf("%v=>", v)
		}
	}
}
