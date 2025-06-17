package com.example.promo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDTO {
    private String patientId;
    private String cartId;
    private String cartStatus;
    private int isLock;
    private String cartType;
    private long patientOrderId;
    private double paidAmount;
    private double amount;
    private String sourceChannel;
    private String prescriptionId;
    private String membershipCode;
    private String corporateDiscount;
    private String voucherCode;
    private double discountAmount;
    private String orderPlacedFor;
    private String medicineEnquiry;
    private String prescriptionDoc;
    private List<MedicineItemDTO> medicineItems;
    private List<LabTestItemDTO> labTestItems;
}
