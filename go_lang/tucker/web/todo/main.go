package todo

import (
	"log"
	"net/http"

	"github.com/guiwoo/tucker_web/todo/app"
	"github.com/joho/godotenv"
)

func Start() {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	m := app.MakeHandler("./test.db")
	defer m.Close()

	log.Fatal(http.ListenAndServe(":3000", m))
}
