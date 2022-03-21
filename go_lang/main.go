package main

func carpet(brown, yellow int) []int {
	var x, y int
	y = 3
	x = (brown-(y*2))/2 + 2

	for y >= 3 {
		if (x-2)*(y-2) == yellow {
			break
		}
		x -= 1
		y += 1
	}
	return []int{x, y}
}

func main() {

}
