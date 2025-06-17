package com.example.promo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "patient_coupon_map")
public class PatientCouponMap {

    @Id
    private String id;

    private String patientId;
    private List<CouponDetail> couponDetails;

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<CouponDetail> getCouponDetails() {
        return couponDetails;
    }

    public void setCouponDetails(List<CouponDetail> couponDetails) {
        this.couponDetails = couponDetails;
    }

    // --- Inner Static Class for Coupon Detail ---
    public static class CouponDetail {
        private String couponCode;
        private String couponName;
        private String startDate;
        private String endDate;

        // Getters and Setters
        public CouponDetail() {
			// TODO Auto-generated constructor stub
		}

        public CouponDetail(String couponCode, String couponName, String startDate, String endDate) {
            this.couponCode = couponCode;
            this.couponName = couponName;
            this.startDate = startDate;
            this.endDate = endDate;
        }
        
		public String getCouponCode() {
            return couponCode;
        }

        public void setCouponCode(String couponCode) {
            this.couponCode = couponCode;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}
