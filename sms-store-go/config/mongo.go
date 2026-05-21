package config

import (
	"context"
	"log"

	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

var SmsCollection *mongo.Collection

func ConnectMongo() {

	client, err := mongo.Connect(
		context.TODO(),
		options.Client().ApplyURI(
			"mongodb://localhost:27017",
		),
	)

	if err != nil {
		log.Fatal(err)
	}

	log.Println("Connected to MongoDB")

	SmsCollection = client.
		Database("smsdb").
		Collection("sms_history")
}