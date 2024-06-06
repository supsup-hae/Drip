package com.univ.drip.service;

import com.univ.drip.entity.Product;
import java.util.List;
import org.springframework.ui.Model;

public interface ProductManageService {
  String registrationProduct(Product product);

  String addProductToCart(Product product);

  void getRoasteryProductList(Model model, String roastertyName);

  void getCategoryProductList(Model model, String categoryName);

  void getConditionProductList(Model model, String conditionName);

  void getIdProductProduct(Model model, String id);

  List<Product> getRoasteryProductList(String id);

  Product findProductById(String id);

  void deleteProductById(String id);


}
