package rest

import (
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"
	"time"

	"github.com/gorilla/mux"
)

var usersMap map[int]*User

//User Struct
type User struct {
	ID        int       `json:"id"`
	FirstName string    `json:"first_name"`
	LastName  string    `json:"last_name"`
	Email     string    `json:"email"`
	CreatedAt time.Time `json:"created_at"`
}

func indexHandler(wr http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(wr, "Hello World")
}

func usersHandler(wr http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case "GET":
		fmt.Fprintf(wr, "Get UserInfo by /users/{id}")
	case "POST":
		user := new(User)
		err := json.NewDecoder(r.Body).Decode(user)
		if err != nil {
			wr.WriteHeader(http.StatusBadRequest)
			fmt.Fprint(wr, err)
			return
		}
		user.ID = len(usersMap)
		user.CreatedAt = time.Now()
		usersMap[user.ID] = user
		wr.WriteHeader(http.StatusCreated)

		data, _ := json.Marshal(user)
		fmt.Fprint(wr, string(data))
	}
}

func getUserInfo89Handler(wr http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)["id"]
	id, err := strconv.Atoi(vars)
	if err != nil {
		wr.WriteHeader(http.StatusBadRequest)
		fmt.Fprint(wr, err)
	}

	user, ok := usersMap[id]
	if !ok {
		wr.WriteHeader(http.StatusOK)
		fmt.Fprint(wr, "No User Id:", id)
	}

	wr.WriteHeader(http.StatusOK)
	wr.Header().Add("Content-type", "application/json")
	data, _ := json.Marshal(user)

	fmt.Fprint(wr, string(data))
}

func NewHandler() http.Handler {
	usersMap = make(map[int]*User)
	mux := mux.NewRouter()

	mux.HandleFunc("/", indexHandler)
	mux.HandleFunc("/users", usersHandler)
	mux.HandleFunc("/users/{id:[0-9]+}", getUserInfo89Handler)
	return mux
}
