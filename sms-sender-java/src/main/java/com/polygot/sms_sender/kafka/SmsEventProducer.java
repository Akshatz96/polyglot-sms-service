package com.polygot.sms_sender.kafka;

import com.polygot.sms_sender.dto.SmsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SmsEventProducer {

    private static final Logger logger =
            LoggerFactory.getLogger(SmsEventProducer.class);

    private static final String TOPIC = "sms-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public SmsEventProducer(
            KafkaTemplate<String, Object> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSmsEvent(
            SmsRequest request,
            String status
    ) {

        SmsEvent event = new SmsEvent(
                request.getUserId(),
                request.getPhoneNumber(),
                request.getMessage(),
                status
        );

        kafkaTemplate.send(TOPIC, event);

        logger.info(
                "Published SMS event to Kafka topic: {}",
                TOPIC
        );
    }
}