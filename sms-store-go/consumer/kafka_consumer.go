package consumer

import (
	"context"
	"encoding/json"
	"log"

	"github.com/segmentio/kafka-go"

	"sms-store-go/model"
	"sms-store-go/repository"
)

func ConsumeKafkaMessages() {

	reader := kafka.NewReader(
		kafka.ReaderConfig{
			Brokers: []string{"localhost:9092"},
			Topic:   "sms-events",
			GroupID: "sms-store-group",
		},
	)

	log.Println("Kafka consumer started")

	for {

		message, err := reader.ReadMessage(
			context.Background(),
		)

		if err != nil {
			log.Println(err)
			continue
		}

		var event model.SmsEvent

		err = json.Unmarshal(
			message.Value,
			&event,
		)

		if err != nil {
			log.Println(err)
			continue
		}

		log.Printf(
			"Received Kafka Event: %+v\n",
			event,
		)

		err = repository.SaveSmsEvent(event)

		if err != nil {
			log.Println(err)
			continue
		}

		log.Println("SMS event stored in MongoDB")
	}
}