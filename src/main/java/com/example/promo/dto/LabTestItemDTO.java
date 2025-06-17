package com.example.promo.dto;

import lombok.Data;

@Data
public class LabTestItemDTO {
    private Long id;
    private String labTestCode;
    private Double amount;
    private Double discountAmount;
    private Double totalAmount;
    private String hospitalId;
    private String locationId;
    private String status;
    private String prescriptionDoc;
    private Double discountPercentage;
    private String labTestName;
    private String labTestType;
}