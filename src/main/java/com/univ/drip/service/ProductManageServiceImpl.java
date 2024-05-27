package com.univ.drip.service;

import com.univ.drip.entity.Product;
import com.univ.drip.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductManageServiceImpl implements ProductManageService {

  private final ProductRepository productRepository;

  @Override
  public String registrationProduct(Product product) {
    return null;
  }

  @Override
  public String addProductToCart(Product product) {
    return null;
  }
}
