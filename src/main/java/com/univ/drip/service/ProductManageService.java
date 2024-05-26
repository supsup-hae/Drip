package com.univ.drip.service;

import com.univ.drip.entity.Product;
import org.springframework.http.ResponseEntity;

public interface ProductManageService {
  ResponseEntity<String> registrationProduct(Product product);

  ResponseEntity<String> addProductToCart(Product product);
}
