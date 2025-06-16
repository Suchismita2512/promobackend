package com.example.promo.controller;

import com.example.promo.ApiResponse;
import com.example.promo.model.Promo;
import com.example.promo.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/promos")
public class PromoController {

    @Autowired
    private PromoRepository promoRepository;

    @PostMapping("/create")
    public ApiResponse<Promo> createPromo(@RequestBody Promo promo) {
        // Generate random coupon code like "PROMO-XYZ123"
        String couponName = promo.getCouponName();
    if (couponName != null && couponName.length() >= 3) {
        String prefix = couponName.substring(0, 3).toUpperCase();
        String digits = couponName.replaceAll("\\D+", ""); // extract numbers
        String generatedCode = prefix + digits;
        promo.setCouponCode(generatedCode);
    } else {
        promo.setCouponCode(""); 
    }

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

@GetMapping("/search")
public ApiResponse<List<Promo>> searchPromos(
        @RequestParam(required = false) String couponName,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate) {

    List<Promo> allPromos = promoRepository.findAll();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    List<Promo> filteredPromos = allPromos.stream()
        .filter(p -> {
            // Optional name filter
            if (couponName != null && !p.getCouponName().toLowerCase().contains(couponName.toLowerCase())) {
                return false;
            }

            // Optional createdDate filter
            if (startDate != null && endDate != null) {
                try {
                    if (p.getCreatedDate() == null) return false;
                    Date start = sdf.parse(startDate);
                    Date end = sdf.parse(endDate);
                    return !p.getCreatedDate().before(start) && !p.getCreatedDate().after(end);
                } catch (Exception e) {
                    return false;
                }
            }

            return true; // if no filter applies, include all
        })
        .collect(Collectors.toList());

    return new ApiResponse<>("success", "Filtered promos", filteredPromos);
}

}
