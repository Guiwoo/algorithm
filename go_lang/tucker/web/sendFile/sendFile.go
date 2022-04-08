package sendfile

import (
	"fmt"
	"io"
	"net/http"
	"os"
)

func uploadHandler(wr http.ResponseWriter, r *http.Request) {
	file, header, err := r.FormFile("upload_file")
	if err != nil {
		wr.WriteHeader(http.StatusBadRequest)
		fmt.Fprint(wr, err)
		return
	}
	defer file.Close()

	dirname := "./uploads"
	os.MkdirAll(dirname, 0777)
	filepath := fmt.Sprintf("%s/%s", dirname, header.Filename)
	newFile, err := os.Create(filepath)
	defer file.Close()

	if err != nil {
		wr.WriteHeader(http.StatusInternalServerError)
		fmt.Fprint(wr, err)
	}
	io.Copy(newFile, file)
	wr.WriteHeader(http.StatusOK)
	fmt.Fprint(wr, filepath)
}

func Start() {
	http.HandleFunc("/uploads", uploadHandler)
	http.Handle("/", http.FileServer(http.Dir("public"))) // file web 서버
}
