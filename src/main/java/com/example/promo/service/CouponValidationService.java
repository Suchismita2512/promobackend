package com.example.promo.service;

import com.example.promo.dto.BenefitsDTO;
import com.example.promo.dto.CouponValidationRequestDTO;
import com.example.promo.dto.CouponValidationResponseDTO;

public interface CouponValidationService {
	BenefitsDTO validateCoupon(BenefitsDTO requestDTO);
}
