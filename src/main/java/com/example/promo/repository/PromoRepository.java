package com.example.promo.repository;

import com.example.promo.model.Promo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface PromoRepository extends MongoRepository<Promo, String> {
    
    // Custom query method to find by couponCode
    Optional<Promo> findByCouponCode(String couponCode);
}
