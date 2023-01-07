package decoapp

import (
	"fmt"
	"net/http"
)

func indexHandler(rw http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(rw, "Hello World")
}

func NewHandlerApp() http.Handler {
	mux := http.NewServeMux()
	mux.HandleFunc("/", indexHandler)
	return mux
}
