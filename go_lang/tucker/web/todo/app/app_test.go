package app

import (
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"net/url"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestTodos(t *testing.T) {
	assert := assert.New(t)
	ts := httptest.NewServer(MakeHandler())
	defer ts.Close()

	resp, err := http.PostForm(ts.URL+"/todos", url.Values{"name": {"test todo"}})
	assert.NoError(err)
	assert.Equal(http.StatusCreated, resp.StatusCode)
	var todo Todo
	err = json.NewDecoder(resp.Body).Decode(&todo)
	assert.NoError(err)
	assert.Equal(todo.Name, "test todo")
	// id1 := todo.ID

	resp, err = http.PostForm(ts.URL+"/todos", url.Values{"name": {"test todo2"}})
	assert.NoError(err)
	assert.Equal(http.StatusCreated, resp.StatusCode)
	err = json.NewDecoder(resp.Body).Decode(&todo)
	assert.NoError(err)
	assert.Equal(todo.Name, "test todo2")
	// id2 := todo.ID

	resp, err = http.Get(ts.URL + "/todos")
	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)
	todos := []*Todo{}
	err = json.NewDecoder(resp.Body).Decode(&todos)

	assert.NoError(err)
	assert.Equal(len(todos), 2)
}
