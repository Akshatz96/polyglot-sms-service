package com.polygot.sms_sender.vendor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ImiConnectSmsVendor {

    private static final Logger logger =
            LoggerFactory.getLogger(ImiConnectSmsVendor.class);

    public boolean sendSms(
            String phoneNumber,
            String message
    ) {

        logger.info("Preparing request for IMIconnect");

        /*
           Simulating IMIconnect API Request

           Real API would look something like:

           POST https://api.imiconnect.in/resources/v1/messaging

           Headers:
           Authorization: App <api-key>

           Body:
           {
              "deliverychannel":"sms",
              "channels":{
                 "sms":{
                    "text":"Hello"
                 }
              },
              "destination":[
                 {
                    "msisdn":["919999999999"]
                 }
              ]
           }
        */

        logger.info(
                "Sending SMS to {} with message: {}",
                phoneNumber,
                message
        );

        boolean success = new Random().nextBoolean();

        if(success) {
            logger.info("IMIconnect SMS delivery SUCCESS");
        } else {
            logger.error("IMIconnect SMS delivery FAILED");
        }

        return success;
    }
}