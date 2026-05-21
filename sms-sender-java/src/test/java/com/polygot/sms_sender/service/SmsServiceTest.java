package com.polygot.sms_sender.service;

import com.polygot.sms_sender.dto.SmsRequest;
import com.polygot.sms_sender.dto.SmsResponse;
import com.polygot.sms_sender.kafka.SmsEventProducer;
import com.polygot.sms_sender.redis.BlocklistService;
import com.polygot.sms_sender.vendor.ImiConnectSmsVendor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SmsServiceTest {

    private ImiConnectSmsVendor vendor;
    private BlocklistService blocklistService;
    private SmsEventProducer smsEventProducer;

    private SmsService smsService;

    @BeforeEach
    void setup() {

        vendor = mock(ImiConnectSmsVendor.class);

        blocklistService = mock(BlocklistService.class);

        smsEventProducer = mock(SmsEventProducer.class);

        smsService = new SmsService(
                vendor,
                blocklistService,
                smsEventProducer
        );
    }

    @Test
    void shouldBlockUser() {

        SmsRequest request = new SmsRequest(
                "u100",
                "9999999999",
                "Blocked test"
        );

        when(
                blocklistService.isBlocked("u100")
        ).thenReturn(true);

        SmsResponse response =
                smsService.sendSms(request);

        assertEquals(
                "BLOCKED",
                response.getStatus()
        );

        verify(vendor, never())
                .sendSms(any(), any());

        verify(smsEventProducer)
                .publishSmsEvent(request, "BLOCKED");
    }

    @Test
    void shouldSendSmsSuccessfully() {

        SmsRequest request = new SmsRequest(
                "u101",
                "9999999999",
                "Success test"
        );

        when(
                blocklistService.isBlocked("u101")
        ).thenReturn(false);

        when(
                vendor.sendSms(any(), any())
        ).thenReturn(true);

        SmsResponse response =
                smsService.sendSms(request);

        assertEquals(
                "SUCCESS",
                response.getStatus()
        );

        verify(smsEventProducer)
                .publishSmsEvent(request, "SUCCESS");
    }

    @Test
    void shouldFailSmsSending() {

        SmsRequest request = new SmsRequest(
                "u102",
                "9999999999",
                "Fail test"
        );

        when(
                blocklistService.isBlocked("u102")
        ).thenReturn(false);

        when(
                vendor.sendSms(any(), any())
        ).thenReturn(false);

        SmsResponse response =
                smsService.sendSms(request);

        assertEquals(
                "FAIL",
                response.getStatus()
        );

        verify(smsEventProducer)
                .publishSmsEvent(request, "FAIL");
    }
}