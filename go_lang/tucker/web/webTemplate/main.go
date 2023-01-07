package webtemplate

import (
	"os"
	"text/template"
)

type User struct {
	Name  string
	Email string
	Age   int
}

func (u User) IsOld() bool {
	return u.Age > 18
}

func TemplateStart() {
	user := User{Name: "Guiwoo", Email: "park.guiwoo@hotmail.com", Age: 13}
	user2 := User{Name: "bbb", Email: "bbbb.guiwoo@hotmail.com", Age: 35}
	users := []User{user, user2}
	tmp1, err := template.New("Tmp1").ParseFiles("webTemplate/tmpl1.tmpl", "webTemplate/tmpl2.tmpl")
	if err != nil {
		panic(err)
	}
	tmp1.ExecuteTemplate(os.Stdout, "tmpl2.tmpl", users)
}
