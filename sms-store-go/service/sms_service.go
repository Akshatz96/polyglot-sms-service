package service

import (
	"sms-store-go/model"
	"sms-store-go/repository"
)

func GetSmsHistory(
	userId string,
) ([]model.SmsEvent, error) {

	return repository.GetSmsHistory(
		userId,
	)
}