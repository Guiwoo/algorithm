package basic

import "fmt"

/**
--- 예제 1번쨰
*/
type Stringer interface {
	String() string
}

type Student struct {
	Name string
	Age  int
}

func (s Student) String() string {
	return fmt.Sprintf("Hoit %s,Huaa%d", s.Name, s.Age)
}

func (s Student) GetAge() int {
	return s.Age
}

func ex_01() {
	student := Student{"guiwoo", 12}
	var stringer Stringer

	stringer = student
	rValue := stringer.String()
	fmt.Println(rValue, student.Age)
}

/**
--- 예제 2번쨰
*/

type Sender interface {
	Send(n string)
}

type PostSender struct {
}

type FedexSender struct {
}

func (p *PostSender) Send(name string) {
	fmt.Printf("우체국에서 택배를 %s보냅니다\n", name)
}

func (f *FedexSender) Send(sopo string) {
	fmt.Printf("Fedex sends %s\n", sopo)
}

func whyInterface() {
	SendBook := func(name string, sender Sender) {
		sender.Send(name)
	}
	var sender Sender = &PostSender{}
	SendBook("어린왕자", sender)
	SendBook("그리스인 조르바", sender)
}

func Start() {
	whyInterface()
}
