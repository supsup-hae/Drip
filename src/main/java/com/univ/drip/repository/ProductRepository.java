package com.univ.drip.repository;

import com.univ.drip.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

  List<Product> findByProductRoastery(String productRoastery);


}