package com.example.promo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineItemDTO {
    private Long Id;
    private String drugId;
    private String drugName;
    private Integer quantity;
    private Double unitPrice;
    private Double discountAmount;
    private Double totalAmount;
    private Boolean prescriptionRequired;
    private String status;
    private String medicineTypeOfSell;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String orderRedemStatus;
    private String drugCategory;
    private Double membershipDiscount;
}
