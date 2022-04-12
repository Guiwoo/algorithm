package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"time"

	"github.com/gorilla/pat"
	"github.com/unrolled/render"
)

var rd render.Render

type User struct {
	Name      string    `json:"name"`
	Email     string    `json:"email"`
	CreatedAt time.Time `json:"created_at"`
}

func getUserInfoHandler(rw http.ResponseWriter, r *http.Request) {
	user := User{Name: "guiwoo", Email: "test@test.com"}

	rd.JSON(rw, http.StatusOK, user)
}

func addUserHandler(rw http.ResponseWriter, r *http.Request) {
	var user User
	err := json.NewDecoder(r.Body).Decode(&user)
	if err != nil {
		rw.WriteHeader(http.StatusBadRequest)
		fmt.Fprint(rw, err)
		rd.Text(rw, http.StatusBadRequest, err.Error())
		return
	}
	user.CreatedAt = time.Now()
	data, _ := json.Marshal(user)
	fmt.Fprint(rw, string(data))
}

func helloHandler(rw http.ResponseWriter, r *http.Request) {
	user := User{Name: "guiwoo", Email: "test@test.com"}
	rd.HTML(rw, http.StatusOK, "hello", user)
}

func main() {
	rd = *render.New(render.Options{
		Extensions: []string{".html", ".tmpl"},
		Layout:     "hello",
	})
	mux := pat.New()

	mux.Get("/users", getUserInfoHandler)
	mux.Post("/users", addUserHandler)
	mux.Get("/hello", helloHandler)

	http.ListenAndServe(":3000", mux)
}
