package rest

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"strconv"
	"strings"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestIndex(t *testing.T) {
	assert := assert.New(t)

	ts := httptest.NewServer(NewHandler())
	defer ts.Close()

	resp, err := http.Get(ts.URL)
	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)
	// 첫번째 url handler 설정 안해주면 패스못함 적어도 한개 아무거나 만들어놓으세요
	data, _ := ioutil.ReadAll(resp.Body)
	assert.Equal("Hello World", string(data))
}

func TestUsers(t *testing.T) {
	assert := assert.New(t)

	ts := httptest.NewServer(NewHandler())
	defer ts.Close()

	resp, err := http.Get(ts.URL + "/users")
	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)
	data, _ := ioutil.ReadAll(resp.Body)
	assert.Equal(string(data), "No Users")
}

func TestGetUserInfo(t *testing.T) {
	assert := assert.New(t)

	ts := httptest.NewServer(NewHandler())
	defer ts.Close()

	resp, err := http.Get(ts.URL + "/users/89")
	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)

	data, _ := ioutil.ReadAll(resp.Body)
	assert.Contains(string(data), "No User Id:89")
}

func TestCreateUser(t *testing.T) {
	assert := assert.New(t)

	ts := httptest.NewServer(NewHandler())
	defer ts.Close()

	resp, err := http.Post(ts.URL+"/users", "appliecation/json", strings.NewReader(`{"fisrt_name":"guiwoo","last_name":"park","email":"park.guiwoo@hotmail.com"}`))
	assert.NoError(err)
	assert.Equal(http.StatusCreated, resp.StatusCode)

	user := new(User)
	err = json.NewDecoder(resp.Body).Decode(user)
	assert.NoError(err)
	assert.Equal(0, user.ID)

	id := user.ID
	resp, err = http.Get(ts.URL + "/users/" + strconv.Itoa(id))
	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)

	user2 := new(User)
	err = json.NewDecoder(resp.Body).Decode(user2)
	assert.NoError(err)
	assert.Equal(user.ID, user2.ID)
	assert.Equal(user.FirstName, user2.FirstName)
}

func TestDeleteUser(t *testing.T) {
	assert := assert.New(t)

	ts := httptest.NewServer(NewHandler())
	defer ts.Close()

	req, _ := http.NewRequest("DELETE", ts.URL+"/users/0", nil)
	resp, err := http.DefaultClient.Do(req)

	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)
	data, _ := ioutil.ReadAll(resp.Body)
	assert.Contains(string(data), "No User Id:0")

	resp, err = http.Post(ts.URL+"/users", "appliecation/json", strings.NewReader(`{"fisrt_name":"guiwoo","last_name":"park","email":"park.guiwoo@hotmail.com"}`))
	assert.NoError(err)
	assert.Equal(http.StatusCreated, resp.StatusCode)

	user := new(User)
	err = json.NewDecoder(resp.Body).Decode(user)
	assert.NoError(err)
	assert.Equal(0, user.ID)

	req, _ = http.NewRequest("DELETE", ts.URL+"/users/0", nil)
	resp, err = http.DefaultClient.Do(req)

	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)
	data, _ = ioutil.ReadAll(resp.Body)
	assert.Contains(string(data), "Deleted user id :0")
}

func TestUpdateUser(t *testing.T) {
	assert := assert.New(t)

	ts := httptest.NewServer(NewHandler())
	defer ts.Close()

	req, _ := http.NewRequest("PUT", ts.URL+"/users", strings.NewReader(`{"id":0,"first_name":"updated","last_name":"updated"}`))
	resp, err := http.DefaultClient.Do(req)
	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)
	data, _ := ioutil.ReadAll(resp.Body)
	assert.Contains(string(data), "No User Id:0")

	resp, err = http.Post(ts.URL+"/users", "appliecation/json", strings.NewReader(`{"fisrt_name":"guiwoo","last_name":"park","email":"park.guiwoo@hotmail.com"}`))
	assert.NoError(err)
	assert.Equal(http.StatusCreated, resp.StatusCode)

	user := new(User)
	err = json.NewDecoder(resp.Body).Decode(user)
	assert.NoError(err)
	assert.Equal(0, user.ID)
	updatedStr := fmt.Sprintf(`{"id":%d,"first_name":"updated"}`, user.ID)

	req, _ = http.NewRequest("PUT", ts.URL+"/users", strings.NewReader(updatedStr))
	resp, err = http.DefaultClient.Do(req)
	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)

	updatedUser := new(User)
	err = json.NewDecoder(resp.Body).Decode(updatedUser)
	assert.NoError(err)
	assert.Equal(updatedUser.ID, user.ID)
	assert.Equal("updated", updatedUser.FirstName)
	assert.Equal(user.LastName, updatedUser.LastName)
	assert.Equal(user.Email, updatedUser.Email)
}

func TestUsersData(t *testing.T) {
	assert := assert.New(t)

	ts := httptest.NewServer(NewHandler())
	defer ts.Close()

	resp, err := http.Post(ts.URL+"/users", "appliecation/json", strings.NewReader(`{"fisrt_name":"guiwoo","last_name":"park","email":"park.guiwoo@hotmail.com"}`))
	assert.NoError(err)
	assert.Equal(http.StatusCreated, resp.StatusCode)

	resp, err = http.Post(ts.URL+"/users", "appliecation/json", strings.NewReader(`{"fisrt_name":"segu","last_name":"go","email":"gosegu@hotmail.com"}`))
	assert.NoError(err)
	assert.Equal(http.StatusCreated, resp.StatusCode)

	resp, err = http.Get(ts.URL + "/users")
	assert.NoError(err)
	assert.Equal(http.StatusOK, resp.StatusCode)

	users := []*User{}
	err = json.NewDecoder(resp.Body).Decode(&users)
	assert.NoError(err)
	assert.Equal(2, len(users))
}
