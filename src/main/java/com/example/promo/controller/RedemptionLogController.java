package com.example.promo.controller;

import com.example.promo.dto.RedemptionRequestDTO;
import com.example.promo.service.RedemptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redemptions")
public class RedemptionLogController {

    @Autowired
    private RedemptionLogService redemptionLogService;

    @PostMapping("/save")
    public String saveRedemption(@RequestBody RedemptionRequestDTO requestDTO) {
        redemptionLogService.saveRedemption(
                requestDTO.getCouponCode(),
                requestDTO.getPatientId(),
                requestDTO.getOrderId(),
                requestDTO.getUserId()
        );
        return "Redemption saved successfully.";
    }

    @GetMapping("/count/user")
    public long getUserRedemptionCount(
            @RequestParam String couponCode,
            @RequestParam String patientId
    ) {
        return redemptionLogService.getUserRedemptionCount(couponCode, patientId);
    }

    @GetMapping("/count/total")
    public long getTotalRedemptionCount(@RequestParam String couponCode) {
        return redemptionLogService.getTotalRedemptionCount(couponCode);
    }

    @GetMapping("/count/today")
    public long getTodayRedemptionCount(@RequestParam String couponCode) {
        return redemptionLogService.getTodayRedemptionCount(couponCode);
    }
}
