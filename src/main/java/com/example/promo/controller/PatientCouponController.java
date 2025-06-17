package com.example.promo.controller;
import com.example.promo.model.PatientCouponMap;
import com.example.promo.repository.PatientCouponMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patient-coupons")
public class PatientCouponController {

    @Autowired
    private PatientCouponMapRepository repository;

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getCouponsByPatientId(@PathVariable String patientId) {
        Optional<PatientCouponMap> optionalMap = repository.findByPatientId(patientId);

        if (optionalMap.isPresent()) {
            return ResponseEntity.ok(optionalMap.get());
        } else {
            return ResponseEntity.status(404).body("No coupons found for patient ID: " + patientId);
        }
    }
}
