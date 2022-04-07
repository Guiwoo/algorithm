package main

import (
	"fmt"
	"net/http"

	"github.com/guiwoo/tucker_web/myapp"
)

const PORT = ":3000"

func main() {
	fmt.Printf("✅ Server is Connected on http://localhost%s/", PORT)
	http.ListenAndServe(PORT, myapp.NewHttpHandler())
}
