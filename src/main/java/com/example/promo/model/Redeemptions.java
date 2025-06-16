package com.example.promo.model;

public class Redeemptions {
    private int totalRedeemCnt;
    private int perDayRedeemCnt;
    private int perUserRedeemCnt;

    public Redeemptions() {
    }

    public int getTotalRedeemCnt() {
        return totalRedeemCnt;
    }

    public void setTotalRedeemCnt(int totalRedeemCnt) {
        this.totalRedeemCnt = totalRedeemCnt;
    }

    public int getPerDayRedeemCnt() {
        return perDayRedeemCnt;
    }

    public void setPerDayRedeemCnt(int perDayRedeemCnt) {
        this.perDayRedeemCnt = perDayRedeemCnt;
    }

    public int getPerUserRedeemCnt() {
        return perUserRedeemCnt;
    }

    public void setPerUserRedeemCnt(int perUserRedeemCnt) {
        this.perUserRedeemCnt = perUserRedeemCnt;
    }
}
