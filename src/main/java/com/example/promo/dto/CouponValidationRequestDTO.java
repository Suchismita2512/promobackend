package com.example.promo.dto;

import lombok.Data;
import java.util.List;

@Data
public class CouponValidationRequestDTO {
    private String couponCode;
    private CartDTO cart;
}
