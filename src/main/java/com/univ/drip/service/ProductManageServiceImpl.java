package com.univ.drip.service;

import com.univ.drip.entity.Product;
import com.univ.drip.repository.ProductRepository;
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
}
