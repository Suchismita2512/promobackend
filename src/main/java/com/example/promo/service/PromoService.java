package com.example.promo.service;

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

    public Promo createPromo(Promo promo) {
        String generatedCode = "PROMO-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        promo.setCouponCode(generatedCode);
        return promoRepository.save(promo);
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
