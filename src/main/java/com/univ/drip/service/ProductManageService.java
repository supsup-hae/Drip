package com.univ.drip.service;

import com.univ.drip.entity.Product;
import org.springframework.http.ResponseEntity;

public interface ProductManageService {
  String registrationProduct(Product product);

  String addProductToCart(Product product);
}
