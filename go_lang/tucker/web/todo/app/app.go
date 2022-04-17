package app

import (
	"fmt"
	"net/http"
	"strconv"
	"time"

	"github.com/gorilla/mux"
	"github.com/unrolled/render"
)

var rd *render.Render

type Todo struct {
	ID        int       `json:"id"`
	Name      string    `json:"name"`
	Completed bool      `json:"completed"`
	CreatedAt time.Time `json:"created_at"`
}

var todoMap map[int]*Todo

func indexHandler(rw http.ResponseWriter, r *http.Request) {
	http.Redirect(rw, r, "/todo.html", http.StatusTemporaryRedirect)
}

func getTodosHandler(rw http.ResponseWriter, r *http.Request) {
	list := []*Todo{}
	for _, v := range todoMap {
		fmt.Println(v)
		list = append(list, v)
	}
	rd.JSON(rw, http.StatusOK, list)
}

func postTodosHandler(rw http.ResponseWriter, r *http.Request) {
	name := r.FormValue("name")
	id := time.Now().Second() + 2
	todo := &Todo{id, name, false, time.Now()}
	todoMap[id] = todo

	rd.JSON(rw, http.StatusCreated, todo)
}

type Success struct {
	Sucess bool `json:"sucess"`
}

func deleteTodoHandler(rw http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)["id"]
	id, _ := strconv.Atoi(vars)
	if _, ok := todoMap[id]; ok {
		delete(todoMap, id)
		rd.JSON(rw, http.StatusOK, Success{true})
	} else {
		rd.JSON(rw, http.StatusOK, Success{false})
	}
}

func toggleHandler(rw http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)["id"]
	id, _ := strconv.Atoi(vars)
	complete := r.FormValue("complete") == "true"

	if todo, ok := todoMap[id]; ok {
		todo.Completed = complete
		rd.JSON(rw, http.StatusOK, Success{true})
	} else {
		rd.JSON(rw, http.StatusOK, Success{false})
		return
	}
}

func MakeHandler() http.Handler {
	todoMap = make(map[int]*Todo)

	rd = render.New()
	r := mux.NewRouter()

	r.HandleFunc("/", indexHandler)
	r.HandleFunc("/todos", getTodosHandler).Methods("GET")
	r.HandleFunc("/todos", postTodosHandler).Methods("POST")
	r.HandleFunc("/todos/{id:[0-9]+}", deleteTodoHandler).Methods("DELETE")
	r.HandleFunc("/toggletodo/{id:[0-9]+}", toggleHandler).Methods("GET")

	return r
}
