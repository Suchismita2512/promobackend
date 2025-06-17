package com.example.promo.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BenefitsDTO {

    public BenefitsDTO() {
        // Default constructor
    }

    public BenefitsDTO(BenefitsDTO requestDTO) {
        // Optional: Copy constructor
    }

    private String patientId;
    private int patientOrderId;
    private String cartId;
    private Double paidAmount;
    private Double amount;
    private double discountAmount;
    private String cartType;
    private String cartStatus;
    private Integer isLock;
    private String couponCode;
    private String sourceChannel;
    private String membershipCode;
    private String prescriptionId;
    private List<LabTestItemDTO> labTestItems;
    private List<MedicineItemDTO> medicineItems;
    private String membershipApplied;
    private String corporateDiscount;
    private String serviceType;
    private boolean valid;
    private String message;
}
