package com.example.promo.controller;

import com.example.promo.dto.BenefitsDTO;
import com.example.promo.dto.CouponValidationResponseDTO;
import com.example.promo.service.CouponValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponValidationService couponValidationService;

    @PostMapping("/validate")
    public BenefitsDTO validateCoupon(@RequestBody BenefitsDTO requestDTO) {
        return couponValidationService.validateCoupon(requestDTO);
    }
    
    @PostMapping("/remove")
    public ResponseEntity<BenefitsDTO> removeCoupon(@RequestBody BenefitsDTO requestDTO) {
        BenefitsDTO response = couponValidationService.removeCoupon(requestDTO);
        return ResponseEntity.ok(response);
    }
}
