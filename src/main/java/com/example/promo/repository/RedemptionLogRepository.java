package com.example.promo.repository;

import com.example.promo.model.RedemptionLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RedemptionLogRepository extends MongoRepository<RedemptionLog, String> {

    long countByCouponCodeAndPatientId(String couponCode, String patientId);

    long countByCouponCode(String couponCode);

    long countByCouponCodeAndRedemptionDateBetween(String couponCode, Date start, Date end);
}
