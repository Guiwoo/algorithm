package pattern

import "fmt"

type pizza interface {
	getPrice() int
}

//=================================

type veggeMania struct{}

func (v *veggeMania) getPrice() int {
	return 15
}

//==================================

type tomatoTopping struct {
	pizza pizza
}

func (t *tomatoTopping) getPrcie() int {
	pizzaPrice := t.pizza.getPrice()
	return pizzaPrice
}

//===================================

type cheeseTopping struct {
	pizza pizza
}

func (c *cheeseTopping) getPrcie() int {
	pizzaPrice := c.pizza.getPrice()
	return pizzaPrice + 10
}

//====================================

func main() {
	pizza := &veggeMania{}

	pizzaWithCheese := &cheeseTopping{
		pizza: pizza,
	}

	pizzaWithTomato := &tomatoTopping{
		pizza: pizza,
	}

	fmt.Println(pizzaWithCheese, pizzaWithTomato)
}
