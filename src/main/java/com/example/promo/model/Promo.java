package com.example.promo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "promos")
public class Promo {
    @Id
    private String id;

    private String couponName;
    private String couponCode;
    private String couponType;
    private boolean applyOnMembership;
    private String startDate;
    private String endDate;
    private Redeemptions redeemptions;
    private Discount discount;
    private BusinessLines businessLines;

    // private Regions regions;
    // private Users users;

    
    @CreatedDate
    private Date createdDate; 

    // ✅ Getters

     public Date getCreatedDate() {
        return createdDate;
    }
    public String getId() {
        return id;
    }

    public String getCouponName() {
        return couponName;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getCouponType() {
        return couponType;
    }

    public boolean isApplyOnMembership() {
        return applyOnMembership;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Redeemptions getRedeemptions() {
        return redeemptions;
    }

    public Discount getDiscount() {
        return discount;
    }

    public BusinessLines getBusinessLines() {
        return businessLines;
    }

    // public Regions getRegions() {
    //     return regions;
    // }

    // public Users getUsers() {
    //     return users;
    // }

    // ✅ Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public void setApplyOnMembership(boolean applyOnMembership) {
        this.applyOnMembership = applyOnMembership;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRedeemptions(Redeemptions redeemptions) {
        this.redeemptions = redeemptions;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setBusinessLines(BusinessLines businessLines) {
        this.businessLines = businessLines;
    }

    // public void setRegions(Regions regions) {
    //     this.regions = regions;
    // }

    // public void setUsers(Users users) {
    //     this.users = users;
    // }
}
