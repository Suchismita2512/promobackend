package com.example.promo.service;

import com.example.promo.dto.CouponBasicDTO;
import com.example.promo.model.Promo;
import com.example.promo.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PromoService {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private PatientCouponService patientCouponService; // Injected properly

    public Promo createPromo(Promo promo) {
        String generatedCode = "PROMO-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        promo.setCouponCode(generatedCode); // Don't forget to set the generated code!

        Promo savedPromo = promoRepository.save(promo);

        // Prepare DTO
        CouponBasicDTO couponDetails = new CouponBasicDTO(
            savedPromo.getCouponCode(),
            savedPromo.getCouponName(),
            savedPromo.getStartDate(),
            savedPromo.getEndDate()
        );

        if (promo.getUsers() != null && promo.getUsers().getPatient() != null) {
            for (String patientId : promo.getUsers().getPatient()) {
                patientCouponService.saveCouponForPatient(patientId, couponDetails); // FIXED call
            }
        }

        return savedPromo;
    }
    public List<Promo> getAllPromos() {
        return promoRepository.findAll();
    }

    public Optional<Promo> getPromoById(String id) {
        return promoRepository.findById(id);
    }

    public Optional<Promo> updatePromo(String id, Promo updatedPromo) {
        return promoRepository.findById(id).map(existingPromo -> {
            existingPromo.setCouponName(updatedPromo.getCouponName());
            existingPromo.setCouponCode(updatedPromo.getCouponCode());
            existingPromo.setCouponType(updatedPromo.getCouponType());
            existingPromo.setApplyOnMembership(updatedPromo.isApplyOnMembership());
            existingPromo.setStartDate(updatedPromo.getStartDate());
            existingPromo.setEndDate(updatedPromo.getEndDate());
            existingPromo.setRedeemptions(updatedPromo.getRedeemptions());
            existingPromo.setDiscount(updatedPromo.getDiscount());
            existingPromo.setBusinessLines(updatedPromo.getBusinessLines());
            return promoRepository.save(existingPromo);
        });
    }

    public boolean deletePromo(String id) {
        if (promoRepository.existsById(id)) {
            promoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
