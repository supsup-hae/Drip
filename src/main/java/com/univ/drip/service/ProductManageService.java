package com.univ.drip.service;

import com.univ.drip.dto.ProductDto;
import com.univ.drip.entity.Product;
import java.io.IOException;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface ProductManageService {


  void getRoasteryProductList(Model model, String roastertyName);

  void getCategoryProductList(Model model, String categoryName);

  void getConditionProductList(Model model, String conditionName);

  void getIdProductProduct(Model model, Long id);

  List<Product> getRoasteryProductList(String id);

  Product findProductById(Long id);

  void deleteProductById(Long id);

  void saveProduct(ProductDto productDto, MultipartFile multipartFile) throws IOException;

  void updateProduct(ProductDto productDto, MultipartFile multipartFile) throws IOException;

  List<Product> getFilteredProducts(String category, String condition, String roastery);
}
