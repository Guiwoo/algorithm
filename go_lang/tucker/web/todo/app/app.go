package app

import (
	"net/http"
	"os"
	"strconv"
	"strings"

	"github.com/gorilla/mux"
	"github.com/gorilla/sessions"
	"github.com/guiwoo/tucker_web/todo/model"
	"github.com/unrolled/render"
	"github.com/urfave/negroni"
)

var rd *render.Render = render.New()
var store *sessions.CookieStore

type AppHandler struct {
	http.Handler
	db model.DBHandler
}

var getSessionId = func(r *http.Request) string {
	session, err := store.Get(r, "session")
	if err != nil {
		return ""
	}

	val := session.Values["id"]
	if val == nil {
		return ""
	}
	return val.(string)
}

func (a *AppHandler) indexHandler(rw http.ResponseWriter, r *http.Request) {
	http.Redirect(rw, r, "/todo.html", http.StatusTemporaryRedirect)
}

func (a *AppHandler) getTodosHandler(rw http.ResponseWriter, r *http.Request) {
	sessionId := getSessionId(r)
	list := a.db.GetTodos(sessionId)
	rd.JSON(rw, http.StatusOK, list)
}

func (a *AppHandler) postTodosHandler(rw http.ResponseWriter, r *http.Request) {
	sessionId := getSessionId(r)
	name := r.FormValue("name")
	todo := a.db.AddTodo(name, sessionId)
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

func CheckSignin(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	if strings.Contains(r.URL.Path, "/signin") || strings.Contains(r.URL.Path, "/auth") {
		next(rw, r)
		return
	}
	sessionId := getSessionId(r)
	if sessionId != "" {
		next(rw, r)
		return
	}
	http.Redirect(rw, r, "/signin.html", http.StatusTemporaryRedirect)
}

func MakeHandler(filepath string) *AppHandler {
	googleOauthConfig.ClientID = os.Getenv("CLIENT_ID")
	googleOauthConfig.ClientSecret = os.Getenv("SECRET_KEY")
	store = sessions.NewCookieStore([]byte(os.Getenv("SESSION_KEY")))

	r := mux.NewRouter()
	n := negroni.New(
		negroni.NewRecovery(),
		negroni.NewLogger(),
		negroni.HandlerFunc(CheckSignin),
		negroni.NewStatic(http.Dir("todo/public")),
	)

	n.UseHandler(r)

	a := &AppHandler{
		Handler: n,
		db:      model.NewDBHander(filepath),
	}

	r.HandleFunc("/auth/google/login", googleLoginHandler)
	r.HandleFunc("/auth/google/callback", googleAuthCallback)

	r.HandleFunc("/", a.indexHandler)
	r.HandleFunc("/todos", a.getTodosHandler).Methods("GET")
	r.HandleFunc("/todos", a.postTodosHandler).Methods("POST")
	r.HandleFunc("/todos/{id:[0-9]+}", a.deleteTodoHandler).Methods("DELETE")
	r.HandleFunc("/toggletodo/{id:[0-9]+}", a.toggleHandler).Methods("GET")

	return a
}
