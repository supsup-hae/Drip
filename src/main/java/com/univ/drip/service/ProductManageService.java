package com.univ.drip.service;

import com.univ.drip.entity.Product;
import org.springframework.ui.Model;

public interface ProductManageService {
  String registrationProduct(Product product);

  String addProductToCart(Product product);

  void getRoasteryProductList(Model model, String roastertyName);
}
