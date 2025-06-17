package com.example.promo.dto;

import java.util.Date;

public class CouponBasicDTO {
    private String couponCode;
    private String couponName;
    private String startDate;
    private String endDate;

    // Constructor
    public CouponBasicDTO(String couponCode, String couponName, String string, String string2) {
        this.couponCode = couponCode;
        this.couponName = couponName;
        this.startDate = string;
        this.endDate = string2;
    }

    // Getters and Setters
    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

    public String getCouponName() { return couponName; }
    public void setCouponName(String couponName) { this.couponName = couponName; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}
