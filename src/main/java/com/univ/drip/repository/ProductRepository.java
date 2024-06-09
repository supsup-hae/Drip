package com.univ.drip.repository;

import com.univ.drip.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  List<Product> findByProductRoastery(String productRoastery);

  List<Product> findByProductCategory(String productCategory);

  List<Product> findByProductCondition(String productCondition);

  Product findByProductId(Long productId);

  void deleteByProductId(Long productId);

  Product findProductByProductId(Long productId);

  List<Product> findByProductCategoryAndProductCondition(String productCategory, String productCondition);

  @Transactional
  @Modifying
  @Query("update Product p set p.quantity = ?1 where p.productId = ?2")
  int updateQuantityByProductId(Integer quantity, Long productId);
}