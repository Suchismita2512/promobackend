package com.example.promo.repository;

import com.example.promo.model.PatientCouponMap;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientCouponMapRepository extends MongoRepository<PatientCouponMap, String> {
    Optional<PatientCouponMap> findByPatientId(String patientId);
}
