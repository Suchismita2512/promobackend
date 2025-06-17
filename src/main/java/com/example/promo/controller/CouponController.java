package com.example.promo.controller;

import com.example.promo.dto.BenefitsDTO;
import com.example.promo.dto.CouponValidationResponseDTO;
import com.example.promo.service.CouponValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponValidationService couponValidationService;

    @PostMapping("/validate")
    public BenefitsDTO validateCoupon(@RequestBody BenefitsDTO requestDTO) {
    	System.out.println("Checking Drug Typee " + requestDTO);
        return couponValidationService.validateCoupon(requestDTO);
    }
}
