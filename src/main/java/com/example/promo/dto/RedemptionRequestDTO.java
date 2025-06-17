package com.example.promo.dto;

import lombok.Data;

@Data
public class RedemptionRequestDTO {
    private String couponCode;
    private String patientId;
    private String orderId;
    private String userId;
}
