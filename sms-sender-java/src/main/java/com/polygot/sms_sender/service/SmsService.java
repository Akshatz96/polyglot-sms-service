package com.polygot.sms_sender.service;

import com.polygot.sms_sender.dto.SmsRequest;
import com.polygot.sms_sender.dto.SmsResponse;
import com.polygot.sms_sender.vendor.ImiConnectSmsVendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.polygot.sms_sender.redis.BlocklistService;

@Service
public class SmsService {

    private static final Logger logger =
            LoggerFactory.getLogger(SmsService.class);

    private final ImiConnectSmsVendor imiConnectSmsVendor;
    private final BlocklistService blocklistService;

    public SmsService(ImiConnectSmsVendor imiConnectSmsVendor,BlocklistService blocklistService) {
        this.imiConnectSmsVendor = imiConnectSmsVendor;
        this.blocklistService = blocklistService;
    }

    public SmsResponse sendSms(SmsRequest request) {

        logger.info(
                "Received SMS request for user {}",
                request.getUserId()
        );
        if(blocklistService.isBlocked(request.getUserId())) {
            
            logger.warn(
                    "Blocked user attempted SMS send: {}",
                    request.getUserId()
            );
            return new SmsResponse(
                    "BLOCKED",
                    "User is blocked from sending SMS"
            );
        }
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