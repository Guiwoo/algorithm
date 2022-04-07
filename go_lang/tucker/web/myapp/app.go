package myapp

import (
	"encoding/json"
	"fmt"
	"net/http"
	"time"
)

type User struct {
	FirstName string    `json:"first_name"`
	LastName  string    `json:"last_name"`
	Email     string    `json:"email"`
	CreatedAt time.Time `json:"create_at"`
}

type fooHandler struct{}

func indexHandlerfunc(wr http.ResponseWriter, r *http.Request) {
	fmt.Fprint(wr, "Hello World")
}

func (f *fooHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	user := new(User)
	err := json.NewDecoder(r.Body).Decode(user)

	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		fmt.Fprint(w, "Bad Request: ", err)
		return
	}

	user.CreatedAt = time.Now()
	data, _ := json.Marshal(user)
	w.WriteHeader(http.StatusOK)
	fmt.Fprint(w, string(data))
}

func barHandler(w http.ResponseWriter, r *http.Request) {
	name := r.URL.Query().Get("name")
	if name == "" {
		name = "World"
	}
	fmt.Fprintf(w, "Hello Bar %s", name)
}

func NewHttpHandler() http.Handler {
	mux := http.NewServeMux()
	mux.HandleFunc("/", indexHandlerfunc)
	mux.HandleFunc("/bar", barHandler)
	mux.Handle("/foo", &fooHandler{})

	return mux
}
