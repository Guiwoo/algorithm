package decohandler

import "net/http"

type DecoratorFunc func(http.ResponseWriter, *http.Request, http.Handler)

type DecoHandler struct {
	fn DecoratorFunc
	h  http.Handler
}

func (d *DecoHandler) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	d.fn(rw, r, d.h)
}

func NewDecoHandler(h http.Handler, fn DecoratorFunc) http.Handler {
	return &DecoHandler{
		fn: fn,
		h:  h,
	}
}
