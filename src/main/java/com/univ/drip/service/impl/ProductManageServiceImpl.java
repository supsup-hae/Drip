package com.univ.drip.service.impl;

import com.univ.drip.entity.Product;
import com.univ.drip.repository.ProductRepository;
import com.univ.drip.service.ProductManageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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


  @Override
  public void getRoasteryProductList(Model model, String roastertyName) {
    List<Product> productList = productRepository.findByProductRoastery(roastertyName);
    model.addAttribute("prodictList", productList);
  }

  @Override
  public void getCategoryProductList(Model model, String categoryName) {
    List<Product> productList = productRepository.findByProductCategory(categoryName);
    model.addAttribute(categoryName + "ProductList", productList);
  }

  @Override
  public void getConditionProductList(Model model, String conditionName) {
    List<Product> productList = productRepository.findByProductCondition(conditionName);
    if (conditionName.equals("AllSeason")) {
      model.addAttribute("AllSeasonProductList", productList);
    } else {
      model.addAttribute("SeasonalProductList", productList);
    }
  }

  @Override
  public void getIdProductProduct(Model model, String id) {
    Product product = productRepository.findByProductId(id);
    model.addAttribute("product", product);
  }

  @Override
  public List<Product> getRoasteryProductList(String roastery) {
    return productRepository.findByProductRoastery(roastery);
  }

  @Override
  public Product findProductById(String id) {
    return productRepository.findById(id).orElseThrow();
  }

  @Override
  public void deleteProductById(String id) {
    productRepository.deleteById(id);
  }
}
