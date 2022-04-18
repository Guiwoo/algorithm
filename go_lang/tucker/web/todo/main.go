package todo

import (
	"log"
	"net/http"

	"github.com/guiwoo/tucker_web/todo/app"
	"github.com/urfave/negroni"
)

func Start() {

	m := app.MakeHandler("./test.db")
	defer m.Close()

	n := negroni.Classic()
	n.Use(negroni.NewStatic(http.Dir("todo/public/original")))
	n.UseHandler(m)

	log.Fatal(http.ListenAndServe(":3000", n))
}
