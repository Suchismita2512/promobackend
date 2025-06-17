package com.example.promo.service;

import com.example.promo.dto.BenefitsDTO;


public interface CouponValidationService {
	BenefitsDTO validateCoupon(BenefitsDTO requestDTO);
	BenefitsDTO removeCoupon(BenefitsDTO requestDTO);
}
