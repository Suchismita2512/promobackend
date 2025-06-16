package com.example.promo.controller;

import com.example.promo.ApiResponse;
import com.example.promo.model.Promo;
import com.example.promo.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/promos")
public class PromoController {

    @Autowired
    private PromoRepository promoRepository;

    @PostMapping("/create")
    public ApiResponse<Promo> createPromo(@RequestBody Promo promo) {
          // Generate random coupon code like "PROMO-XYZ123"
    String generatedCode = "PROMO-" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    promo.setCouponCode(generatedCode);


        Promo saved = promoRepository.save(promo);
        return new ApiResponse<>("success", "Promo created successfully", saved);
    }

    // ✅ Get All Promos
    @GetMapping("/all")
    public ApiResponse<List<Promo>> getAllPromos() {
        List<Promo> promos = promoRepository.findAll();
        return new ApiResponse<>("success", "All promos fetched", promos);
    }

    // ✅ Get Promo by ID
    @GetMapping("get/{id}")
    public ApiResponse<Promo> getPromoById(@PathVariable String id) {
        Optional<Promo> promo = promoRepository.findById(id);
        return promo
                .map(value -> new ApiResponse<>("success", "Promo found", value))
                .orElseGet(() -> new ApiResponse<>("error", "Promo not found with id: " + id, null));
    }

    // ✅ Update Promo
    @PutMapping("/update/{id}")
    public ApiResponse<Promo> updatePromo(@PathVariable String id, @RequestBody Promo updatedPromo) {
        return promoRepository.findById(id)
                .map(existingPromo -> {
                    existingPromo.setCouponName(updatedPromo.getCouponName());
                    existingPromo.setCouponCode(updatedPromo.getCouponCode());
                    existingPromo.setCouponType(updatedPromo.getCouponType());
                    existingPromo.setApplyOnMembership(updatedPromo.isApplyOnMembership());
                    existingPromo.setStartDate(updatedPromo.getStartDate());
                    existingPromo.setEndDate(updatedPromo.getEndDate());
                    existingPromo.setRedeemptions(updatedPromo.getRedeemptions());
                    existingPromo.setDiscount(updatedPromo.getDiscount());
                    existingPromo.setBusinessLines(updatedPromo.getBusinessLines());
                    Promo updated = promoRepository.save(existingPromo);
                    return new ApiResponse<>("success", "Promo updated successfully", updated);
                })
                .orElseGet(() -> new ApiResponse<>("error", "Promo not found with id: " + id, null));
    }

    // ✅ Delete Promo
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deletePromo(@PathVariable String id) {
        if (promoRepository.existsById(id)) {
            promoRepository.deleteById(id);
            return new ApiResponse<>("success", "Promo with ID " + id + " deleted successfully!", null);
        } else {
            return new ApiResponse<>("error", "Promo not found with id: " + id, null);
        }
    }

}
