package com.polygot.sms_sender.service;

import com.polygot.sms_sender.dto.SmsRequest;
import com.polygot.sms_sender.dto.SmsResponse;
import com.polygot.sms_sender.vendor.ImiConnectSmsVendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private static final Logger logger =
            LoggerFactory.getLogger(SmsService.class);

    private final ImiConnectSmsVendor imiConnectSmsVendor;

    public SmsService(ImiConnectSmsVendor imiConnectSmsVendor) {
        this.imiConnectSmsVendor = imiConnectSmsVendor;
    }

    public SmsResponse sendSms(SmsRequest request) {

        logger.info(
                "Received SMS request for user {}",
                request.getUserId()
        );

        boolean success = imiConnectSmsVendor.sendSms(
                request.getPhoneNumber(),
                request.getMessage()
        );

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