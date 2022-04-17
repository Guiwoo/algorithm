package app

import (
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
	"github.com/guiwoo/tucker_web/todo/model"
	"github.com/unrolled/render"
)

var rd *render.Render

func indexHandler(rw http.ResponseWriter, r *http.Request) {
	http.Redirect(rw, r, "/todo.html", http.StatusTemporaryRedirect)
}

func getTodosHandler(rw http.ResponseWriter, r *http.Request) {
	list := model.GetTodos()
	rd.JSON(rw, http.StatusOK, list)
}

func postTodosHandler(rw http.ResponseWriter, r *http.Request) {
	name := r.FormValue("name")
	todo := model.AddTodo(name)
	rd.JSON(rw, http.StatusCreated, todo)
}

type Success struct {
	Sucess bool `json:"sucess"`
}

func deleteTodoHandler(rw http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)["id"]
	id, _ := strconv.Atoi(vars)
	ok := model.RemoveTodo(id)
	if ok {
		rd.JSON(rw, http.StatusOK, Success{true})
	} else {
		rd.JSON(rw, http.StatusOK, Success{false})
	}
}

func toggleHandler(rw http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)["id"]
	id, _ := strconv.Atoi(vars)
	complete := r.FormValue("complete") == "true"
	ok := model.CompleteTodo(id, complete)
	if ok {
		rd.JSON(rw, http.StatusOK, Success{true})
	} else {
		rd.JSON(rw, http.StatusOK, Success{false})
	}
}

func MakeHandler() http.Handler {

	rd = render.New()
	r := mux.NewRouter()

	r.HandleFunc("/", indexHandler)
	r.HandleFunc("/todos", getTodosHandler).Methods("GET")
	r.HandleFunc("/todos", postTodosHandler).Methods("POST")
	r.HandleFunc("/todos/{id:[0-9]+}", deleteTodoHandler).Methods("DELETE")
	r.HandleFunc("/toggletodo/{id:[0-9]+}", toggleHandler).Methods("GET")

	return r
}
