package com.example.promo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "redemption_logs")
public class RedemptionLog {

    @Id
    private String id;

    private String couponCode;
    private String patientId;
    private String orderId;
    private String userId; // optional
    private Date redemptionDate;

    // Getters
    public String getId() { return id; }
    public String getCouponCode() { return couponCode; }
    public String getPatientId() { return patientId; }
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public Date getRedemptionDate() { return redemptionDate; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setRedemptionDate(Date redemptionDate) { this.redemptionDate = redemptionDate; }
}
