package handler

import (
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/gin-gonic/gin"
)

func TestHealthRoute(t *testing.T) {

	router := gin.Default()

	router.GET(
		"/health",
		func(c *gin.Context) {

			c.JSON(
				200,
				gin.H{
					"status": "Go service running",
				},
			)
		},
	)

	req, _ := http.NewRequest(
		"GET",
		"/health",
		nil,
	)

	response := httptest.NewRecorder()

	router.ServeHTTP(
		response,
		req,
	)

	if response.Code != http.StatusOK {

		t.Errorf(
			"Expected status 200 but got %d",
			response.Code,
		)
	}
}