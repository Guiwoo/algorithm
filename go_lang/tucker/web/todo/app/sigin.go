package app

import (
	"context"
	"crypto/rand"
	"encoding/base64"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"time"

	"golang.org/x/oauth2"
	"golang.org/x/oauth2/google"
)

var googleOauthConfig = oauth2.Config{
	RedirectURL:  "http://localhost:3000/auth/google/callback",
	ClientID:     "",
	ClientSecret: "",
	Scopes:       []string{"https://www.googleapis.com/auth/userinfo.email"},
	Endpoint:     google.Endpoint,
}

// id": "111032300518963042786",
//   "email": "pbk12568@gmail.com",
//   "verified_email": true,
//   "picture": "https://lh3.g

type GoogleUserId struct {
	ID             string `json:"id"`
	Email          string `json:"email"`
	Verified_Email bool   `json:"verified_email"`
	Picture        string `json:"picture"`
}

func generateStateOauthCookie(rw http.ResponseWriter) string {
	expiration := time.Now().Add(1 * 24 * time.Hour)

	b := make([]byte, 16)
	rand.Read(b)
	state := base64.URLEncoding.EncodeToString(b)
	cookie := &http.Cookie{
		Name:    "oauthstate",
		Value:   state,
		Expires: expiration,
	}
	http.SetCookie(rw, cookie)
	return state
}

func googleLoginHandler(rw http.ResponseWriter, r *http.Request) {
	state := generateStateOauthCookie(rw)
	url := googleOauthConfig.AuthCodeURL(state)
	http.Redirect(rw, r, url, http.StatusTemporaryRedirect)
}

const oauthGoogleUrlAPI = "https://www.googleapis.com/oauth2/v2/userinfo?access_token="

func getGoogleUserInfo(code string) ([]byte, error) {
	token, err := googleOauthConfig.Exchange(context.Background(), code)
	if err != nil {
		return nil, fmt.Errorf("Failed to Exchange %s\n", err.Error())
	}
	resp, err := http.Get(oauthGoogleUrlAPI + token.AccessToken)
	if err != nil {
		return nil, fmt.Errorf("Failed to get user info %s\n", err.Error())
	}

	return ioutil.ReadAll(resp.Body)
}

func googleAuthCallback(rw http.ResponseWriter, r *http.Request) {
	oauthstate, _ := r.Cookie("oauthstate")
	errMsg := fmt.Sprintf("Invalid google oauth state cookie: %s, state: %s", oauthstate.Value, r.FormValue("state"))

	if r.FormValue("state") != oauthstate.Value {
		log.Print(errMsg)
		http.Error(rw, errMsg, http.StatusInternalServerError)
		// http.Redirect(rw, r, "/", http.StatusTemporaryRedirect)
		return
	}
	var result GoogleUserId
	data, err := getGoogleUserInfo(r.FormValue("code"))
	if err != nil {
		log.Println(err.Error())
		http.Error(rw, errMsg, http.StatusInternalServerError)
		return
	}
	//store ID ifno session cookie
	err = json.Unmarshal(data, &result)
	if err != nil {
		log.Println(err.Error())
		http.Redirect(rw, r, "/", http.StatusTemporaryRedirect)
		return
	}
	session, _ := store.Get(r, "session")

	session.Values["id"] = result.ID
	err = session.Save(r, rw)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
		return
	}
	http.Redirect(rw, r, "/", http.StatusTemporaryRedirect)
}
