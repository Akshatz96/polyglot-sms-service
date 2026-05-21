package routes

import (
	"github.com/gin-gonic/gin"

	"sms-store-go/handler"
)

func RegisterRoutes(router *gin.Engine) {

	router.GET(
		"/v1/sms/history/:userId",
		handler.GetSmsHistory,
	)

	router.GET(
		"/health",
		func(c *gin.Context) {

			c.JSON(200, gin.H{
				"status": "Go service running",
			})
		},
	)
}