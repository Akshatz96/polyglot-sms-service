package model

type SmsEvent struct {
	UserId      string `json:"userId" bson:"userId"`
	PhoneNumber string `json:"phoneNumber" bson:"phoneNumber"`
	Message     string `json:"message" bson:"message"`
	Status      string `json:"status" bson:"status"`
}