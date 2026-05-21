package com.polygot.sms_sender.controller;

import com.polygot.sms_sender.dto.SmsRequest;

import com.polygot.sms_sender.dto.SmsResponse;

import com.polygot.sms_sender.service.SmsService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/v1/sms")

public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {

        this.smsService = smsService;

    }

    @PostMapping("/send")

    public ResponseEntity<SmsResponse> sendSms(

            @Valid@RequestBody SmsRequest request

    ) {

        SmsResponse response = smsService.sendSms(request);

        return ResponseEntity.ok(response);

    }

}