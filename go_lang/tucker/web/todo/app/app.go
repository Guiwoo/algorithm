package app

import (
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
	"github.com/guiwoo/tucker_web/todo/model"
	"github.com/unrolled/render"
)

var rd *render.Render = render.New()

type AppHandler struct {
	http.Handler
	db model.DBHandler
}

func (a *AppHandler) indexHandler(rw http.ResponseWriter, r *http.Request) {
	http.Redirect(rw, r, "/todo.html", http.StatusTemporaryRedirect)
}

func (a *AppHandler) getTodosHandler(rw http.ResponseWriter, r *http.Request) {
	list := a.db.GetTodos()
	rd.JSON(rw, http.StatusOK, list)
}

func (a *AppHandler) postTodosHandler(rw http.ResponseWriter, r *http.Request) {
	name := r.FormValue("name")
	todo := a.db.AddTodo(name)
	rd.JSON(rw, http.StatusCreated, todo)
}

type Success struct {
	Sucess bool `json:"sucess"`
}

func (a *AppHandler) deleteTodoHandler(rw http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)["id"]
	id, _ := strconv.Atoi(vars)
	ok := a.db.RemoveTodo(id)
	if ok {
		rd.JSON(rw, http.StatusOK, Success{true})
	} else {
		rd.JSON(rw, http.StatusOK, Success{false})
	}
}

func (a *AppHandler) toggleHandler(rw http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)["id"]
	id, _ := strconv.Atoi(vars)
	complete := r.FormValue("complete") == "true"
	ok := a.db.CompleteTodo(id, complete)
	if ok {
		rd.JSON(rw, http.StatusOK, Success{true})
	} else {
		rd.JSON(rw, http.StatusOK, Success{false})
	}
}
func (a *AppHandler) Close() {
	a.db.Close()
}

func MakeHandler(filepath string) *AppHandler {
	r := mux.NewRouter()
	a := &AppHandler{
		Handler: r,
		db:      model.NewDBHander(filepath),
	}

	r.HandleFunc("/", a.indexHandler)
	r.HandleFunc("/todos", a.getTodosHandler).Methods("GET")
	r.HandleFunc("/todos", a.postTodosHandler).Methods("POST")
	r.HandleFunc("/todos/{id:[0-9]+}", a.deleteTodoHandler).Methods("DELETE")
	r.HandleFunc("/toggletodo/{id:[0-9]+}", a.toggleHandler).Methods("GET")

	return a
}
