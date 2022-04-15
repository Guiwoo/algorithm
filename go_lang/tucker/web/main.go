package main

import (
	"log"

	oauth "github.com/guiwoo/tucker_web/OAuth"
	"github.com/joho/godotenv"
)

func main() {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	oauth.Start()
}
