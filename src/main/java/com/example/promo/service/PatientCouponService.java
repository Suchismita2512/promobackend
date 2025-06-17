package com.example.promo.service;

import com.example.promo.dto.CouponBasicDTO;
import com.example.promo.model.PatientCouponMap;
import com.example.promo.model.PatientCouponMap.CouponDetail;
import com.example.promo.repository.PatientCouponMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientCouponService {

    @Autowired
    private PatientCouponMapRepository repository;

    public void saveCouponForPatient(String patientId, CouponBasicDTO couponInfo) {
        Optional<PatientCouponMap> existing = repository.findByPatientId(patientId);
        System.out.println( patientId + "   " + existing);

        if (!existing.isPresent()) {
            // No entry for this patient, create a new one
            PatientCouponMap newEntry = new PatientCouponMap();
            newEntry.setPatientId(patientId);

            List<CouponDetail> coupons = new ArrayList<>();
            coupons.add(new CouponDetail(
                couponInfo.getCouponCode(),
                couponInfo.getCouponName(),
                couponInfo.getStartDate(),
                couponInfo.getEndDate()
            ));
            newEntry.setCouponDetails(coupons);
            repository.save(newEntry);
        } else {
            PatientCouponMap existingMap = existing.get();

            boolean alreadyExists = existingMap.getCouponDetails().stream()
                .anyMatch(c -> c.getCouponCode().equalsIgnoreCase(couponInfo.getCouponCode()));

            if (!alreadyExists) {
                existingMap.getCouponDetails().add(new CouponDetail(
                    couponInfo.getCouponCode(),
                    couponInfo.getCouponName(),
                    couponInfo.getStartDate(),
                    couponInfo.getEndDate()
                ));
                repository.save(existingMap);
            }
        }
    }
}
