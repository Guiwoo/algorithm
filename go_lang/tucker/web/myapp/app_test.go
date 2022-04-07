package myapp

import (
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestIndexPathHandler(t *testing.T) {
	assert := assert.New(t) // test를 도와주는 패키지

	res := httptest.NewRecorder()
	req := httptest.NewRequest("GET", "/", nil)

	mux := NewHttpHandler() // mux 를 ㅣ용해서 했기때문에
	mux.ServeHTTP(res, req) // 분기를 다시 보내주어야 함

	assert.Equal(http.StatusOK, res.Code)
	data, _ := ioutil.ReadAll(res.Body)
	assert.Equal("Hello World", string(data))
}

func TestBarHandler_withOutname(t *testing.T) {
	assert := assert.New(t) // test를 도와주는 패키지

	res := httptest.NewRecorder()
	req := httptest.NewRequest("GET", "/bar", nil)

	mux := NewHttpHandler() // mux 를 ㅣ용해서 했기때문에
	mux.ServeHTTP(res, req) // 분기를 다시 보내주어야 함

	assert.Equal(http.StatusOK, res.Code)
	data, _ := ioutil.ReadAll(res.Body)
	assert.Equal("Hello Bar World", string(data))
}

func TestBarHandler_withName(t *testing.T) {
	assert := assert.New(t) // test를 도와주는 패키지

	res := httptest.NewRecorder()
	req := httptest.NewRequest("GET", "/bar?name=guiwoo", nil)

	mux := NewHttpHandler() // mux 를 ㅣ용해서 했기때문에
	mux.ServeHTTP(res, req) // 분기를 다시 보내주어야 함

	assert.Equal(http.StatusOK, res.Code)
	data, _ := ioutil.ReadAll(res.Body)
	assert.Equal("Hello Bar guiwoo", string(data))
}
