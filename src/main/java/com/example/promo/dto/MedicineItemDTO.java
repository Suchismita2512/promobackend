package com.example.promo.dto;

import lombok.Data;

@Data
public class MedicineItemDTO {
    private String drugId;
    private String drugName;
    private int quantity;
    private double unitPrice;
    private double discountAmount;
    private double totalAmount;
    private boolean prescriptionRequired;
    private String status;
    private String medicineTypeOfSell;
    private String createdBy;
    private String modifiedBy;
    private String createdDate;
    private String modifiedDate;
    private String orderRedemStatus;
    private String drugCategory;
    private String membershipDiscount;
    private long id;
}
