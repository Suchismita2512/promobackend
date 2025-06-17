package com.example.promo.dto;

import lombok.Data;

@Data
public class LabTestItemDTO {
    private String testCode;
    private String labType;
    private String labName;
    private double unitPrice;
    private double totalAmount;
    private double discountAmount;
}
