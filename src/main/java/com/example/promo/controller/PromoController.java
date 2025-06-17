package com.example.promo.controller;

import com.example.promo.ApiResponse;
import com.example.promo.model.Promo;
import com.example.promo.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/promos")
public class PromoController {

    @Autowired
    private PromoService promoService;

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
}
