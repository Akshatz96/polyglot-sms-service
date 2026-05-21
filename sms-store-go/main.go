package main

import (
	"github.com/gin-gonic/gin"

	"sms-store-go/config"
	"sms-store-go/consumer"
	"sms-store-go/routes"
)

func main() {

	config.ConnectMongo()

	go consumer.ConsumeKafkaMessages()

	router := gin.Default()

	routes.RegisterRoutes(router)

	router.Run(":8081")
}