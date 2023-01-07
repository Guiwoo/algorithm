package decorator

import (
	"fmt"

	"github.com/tuckersGo/goWeb/web9/cipher"
	"github.com/tuckersGo/goWeb/web9/lzw"
)

type Component interface {
	Operator(string)
}

var sentData string
var reciveData string

type SendComponent struct {
}

func (s *SendComponent) Operator(data string) {
	//send data
	sentData = data
}

// Those are Zipping && Encrypting
type ZipComponent struct {
	com Component
}

func (z *ZipComponent) Operator(data string) {
	writeZipData, err := lzw.Write([]byte(data))
	if err != nil {
		panic(err)
	}
	z.com.Operator(string(writeZipData))
}

type EncryptComponent struct {
	com Component
	key string
}

func (e *EncryptComponent) Operator(data string) {
	encryptByteData, err := cipher.Encrypt([]byte(data), e.key)
	if err != nil {
		panic(err)
	}
	e.com.Operator(string(encryptByteData))
}

// Decrypting && unzip

type DecryptComponent struct {
	key string
	com Component
}

func (d *DecryptComponent) Operator(data string) {
	decryptByteData, err := cipher.Decrypt([]byte(data), d.key)
	if err != nil {
		panic(err)
	}
	d.com.Operator(string(decryptByteData))
}

type UnzipComponent struct {
	com Component
}

func (u *UnzipComponent) Operator(data string) {
	unzipByteData, err := lzw.Read([]byte(data))
	if err != nil {
		panic(err)
	}
	u.com.Operator(string(unzipByteData))
}

type ReadComponent struct{}

func (r *ReadComponent) Operator(data string) {
	reciveData = data
}

func StartPractice() {
	sender := &EncryptComponent{key: "work?",
		com: &ZipComponent{
			com: &SendComponent{},
		},
	}

	sender.Operator("Hello World")
	reciver := &UnzipComponent{
		com: &DecryptComponent{
			key: "work?",
			com: &ReadComponent{},
		},
	}
	reciver.Operator(sentData)
	fmt.Println(reciveData)
}
