package com.univ.drip.controller;

import com.univ.drip.entity.Product;
import com.univ.drip.service.ProductManageService;
import com.univ.drip.service.ProductManageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final ProductManageService productManageService;

  @Autowired
  public ProductController(ProductManageServiceImpl productManageService) {
    this.productManageService = productManageService;
  }

  @PostMapping("/register")
  public String registerProduct(@RequestBody Product product) {
    return productManageService.registrationProduct(product);
  }

  @PostMapping("/cart/add")
  public String addProductToCart(@RequestBody Product product) {
    return productManageService.addProductToCart(product);
  }

  @GetMapping("/productInfo")
  public String moveToProductInfo() {
    return "productInfo";
  }

  @GetMapping("/cart")
  public String moveToCart() {
    return "cart";
  }
}
