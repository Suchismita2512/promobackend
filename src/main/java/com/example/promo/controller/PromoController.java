package com.example.promo.controller;

import com.example.promo.ApiResponse;
import com.example.promo.model.Promo;
import com.example.promo.repository.PromoRepository;
import com.example.promo.service.PromoService;
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
    private PromoService promoService;

    @Autowired
    PromoRepository promoRepository;

     @PostMapping("/create")
    public ApiResponse<Promo> createPromo(@RequestBody Promo promo) {
        Promo saved = promoService.createPromo(promo);
        return new ApiResponse<>("success", "Promo created successfully", saved);
    }


    @GetMapping("/all")
    public ApiResponse<List<Promo>> getAllPromos() {
        List<Promo> promos = promoService.getAllPromos();
        return new ApiResponse<>("success", "All promos fetched", promos);
    }

    @GetMapping("get/{id}")
    public ApiResponse<Promo> getPromoById(@PathVariable String id) {
        Optional<Promo> promo = promoService.getPromoById(id);
        return promo
                .map(value -> new ApiResponse<>("success", "Promo found", value))
                .orElseGet(() -> new ApiResponse<>("error", "Promo not found with id: " + id, null));
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Promo> updatePromo(@PathVariable String id, @RequestBody Promo updatedPromo) {
        Optional<Promo> updated = promoService.updatePromo(id, updatedPromo);
        return updated
                .map(p -> new ApiResponse<>("success", "Promo updated successfully", p))
                .orElseGet(() -> new ApiResponse<>("error", "Promo not found with id: " + id, null));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deletePromo(@PathVariable String id) {
        boolean deleted = promoService.deletePromo(id);
        if (deleted) {
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
