package handler

import (
	"net/http"

	"github.com/gin-gonic/gin"

	"sms-store-go/repository"
)

func GetSmsHistory(c *gin.Context) {

	userId := c.Param("userId")

	results, err := repository.GetSmsHistory(
		userId,
	)

	if err != nil {

		c.JSON(
			http.StatusInternalServerError,
			gin.H{
				"error": err.Error(),
			},
		)

		return
	}

	c.JSON(
		http.StatusOK,
		results,
	)
}