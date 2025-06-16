package com.example.promo.repository;

import com.example.promo.model.Promo;
import org.springframework.data.mongodb.repository.MongoRepository;

// This will allow you to do CRUD operations on the Promo collection
public interface PromoRepository extends MongoRepository<Promo, String> {
}
