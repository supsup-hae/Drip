package com.univ.drip.repository;

import com.univ.drip.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  List<Product> findByProductRoastery(String productRoastery);

  List<Product> findByProductCategory(String productCategory);

  List<Product> findByProductCondition(String productCondition);

  Product findByProductId(Long productId);

  void deleteByProductId(Long productId);

  Product findProductByProductId(Long productId);
}