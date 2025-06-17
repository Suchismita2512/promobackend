package com.example.promo.service;

import com.example.promo.model.RedemptionLog;
import com.example.promo.repository.RedemptionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class RedemptionLogService {

    @Autowired
    private RedemptionLogRepository redemptionLogRepository;

    public void saveRedemption(String couponCode, String patientId, String orderId, String userId) {
        RedemptionLog log = new RedemptionLog();
        log.setCouponCode(couponCode);
        log.setPatientId(patientId);
        log.setOrderId(orderId);
        log.setUserId(userId);
        log.setRedemptionDate(new Date());

        redemptionLogRepository.save(log);
    }

    public long getUserRedemptionCount(String couponCode, String patientId) {
        return redemptionLogRepository.countByCouponCodeAndPatientId(couponCode, patientId);
    }

    public long getTotalRedemptionCount(String couponCode) {
        return redemptionLogRepository.countByCouponCode(couponCode);
    }

    public long getTodayRedemptionCount(String couponCode) {
        Date startOfDay = getStartOfToday();
        Date endOfDay = getEndOfToday();
        return redemptionLogRepository.countByCouponCodeAndRedemptionDateBetween(couponCode, startOfDay, endOfDay);
    }

    private Date getStartOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0); cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date getEndOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59); cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
}
