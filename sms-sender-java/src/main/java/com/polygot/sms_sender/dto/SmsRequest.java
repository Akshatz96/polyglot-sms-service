package com.polygot.sms_sender.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SmsRequest {

    @NotBlank(message = "User ID cannot be empty")
    private String userId;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Message cannot be empty")
    @Size(
            min = 1,
            max = 160,
            message = "Message must be between 1 and 160 characters"
    )
    private String message;

    public SmsRequest() {
    }

    public SmsRequest(String userId, String phoneNumber, String message) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}