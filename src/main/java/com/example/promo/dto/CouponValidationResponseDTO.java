package com.example.promo.dto;

import lombok.Data;

@Data
public class CouponValidationResponseDTO {
    private boolean valid;                 // true if coupon is applicable
    private String message;               // descriptive message
    private String errorCode;             // for specific failure (e.g., "COUPON_EXPIRED")
    private String couponCode;            // returned back for reference
    private CartDTO cart;                 // optional: updated cart object with modified pricing
}
