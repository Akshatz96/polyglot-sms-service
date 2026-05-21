package com.polygot.sms_sender.service;

import com.polygot.sms_sender.dto.SmsRequest;
import com.polygot.sms_sender.dto.SmsResponse;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsService {

    public SmsResponse sendSms(SmsRequest request) {

        boolean success = new Random().nextBoolean();

        if(success) {

            return new SmsResponse(
                    "SUCCESS",
                    "SMS sent successfully"
            );
        }

        return new SmsResponse(
                "FAIL",
                "SMS sending failed"
        );
    }
}