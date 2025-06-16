package com.example.promo.repository;

import com.example.promo.model.Promo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

// This will allow you to do CRUD operations on the Promo collection
public interface PromoRepository extends MongoRepository<Promo, String> {
    List<Promo> findByCouponNameContainingIgnoreCase(String couponName);
    List<Promo> findByCreatedDateBetween(Date startDate, Date endDate);
}
