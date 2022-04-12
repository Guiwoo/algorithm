package decorator

import (
	"log"
	"net/http"
	"time"

	decoapp "github.com/guiwoo/tucker_web/decorator/decoApp"
	decohandler "github.com/guiwoo/tucker_web/decorator/decoHandler"
)

const PORT = ":3000"

func logger(rs http.ResponseWriter, r *http.Request, h http.Handler) {

	start := time.Now()
	log.Println("[LOGGER1] Started")
	h.ServeHTTP(rs, r)
	log.Println("[LOGGER1] Completed time:", time.Since(start).Milliseconds())

}

func logger2(rs http.ResponseWriter, r *http.Request, h http.Handler) {

	start := time.Now()
	log.Println("[LOGGER2] Started")
	h.ServeHTTP(rs, r)
	log.Println("[LOGGER2] Completed time:", time.Since(start).Milliseconds())

}

func NewHandler() http.Handler {
	mux := decoapp.NewHandlerApp()
	mux = decohandler.NewDecoHandler(mux, logger)
	mux = decohandler.NewDecoHandler(mux, logger2)
	return mux
}

func DecoStart() {
	mux := NewHandler()

	http.ListenAndServe(PORT, mux)
}
