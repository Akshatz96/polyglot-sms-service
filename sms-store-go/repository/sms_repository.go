package repository

import (
	"context"

	"sms-store-go/config"
	"sms-store-go/model"
)

func SaveSmsEvent(event model.SmsEvent) error {

	_, err := config.SmsCollection.InsertOne(
		context.Background(),
		event,
	)

	return err
}

func GetSmsHistory(userId string) ([]model.SmsEvent, error) {

	cursor, err := config.SmsCollection.Find(
		context.Background(),
		map[string]interface{}{
			"userId": userId,
		},
	)

	if err != nil {
		return nil, err
	}

	var results []model.SmsEvent

	err = cursor.All(
		context.Background(),
		&results,
	)

	return results, err
}