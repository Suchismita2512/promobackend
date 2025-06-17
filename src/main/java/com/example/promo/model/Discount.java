package com.example.promo.model;

import java.util.List;

public class Discount {
    private String discountType;
    // private int minCartValue;
    // private int maxDiscountAmount;
    private List<DiscountRange> range;

    public Discount() {
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    // public int getMinCartValue() {
    //     return minCartValue;
    // }

    // public void setMinCartValue(int minCartValue) {
    //     this.minCartValue = minCartValue;
    // }

    // public int getMaxDiscountAmount() {
    //     return maxDiscountAmount;
    // }

    // public void setMaxDiscountAmount(int maxDiscountAmount) {
    //     this.maxDiscountAmount = maxDiscountAmount;
    // }

    public List<DiscountRange> getRange() {
        return range;
    }

    public void setRange(List<DiscountRange> range) {
        this.range = range;
    }
    public int getDiscountValue(double amount) {
        if (range == null) {
            return 0;
        }

        for (DiscountRange r : range) {
            double start = r.getStartRange();
            double end = r.getEndRange();

            boolean inRange = (amount >= start) && (end == -1 || amount <= end);
            if (inRange) {
                System.out.println("Match found. Discount = " + r.getDiscountValue());
                return r.getDiscountValue();
            }
        }
        return 0;
    }


}
