package com.univ.drip.service.impl;

import com.univ.drip.dto.ProductDto;
import com.univ.drip.entity.Product;
import com.univ.drip.repository.ProductRepository;
import com.univ.drip.service.ProductManageService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductManageServiceImpl implements ProductManageService {

  private final ProductRepository productRepository;


  @Override
  @Transactional
  public void getRoasteryProductList(Model model, String roastertyName) {
    List<Product> productList = productRepository.findByProductRoastery(roastertyName);
    model.addAttribute("productList", productList);
  }

  @Override
  @Transactional
  public void getCategoryProductList(Model model, String categoryName) {
    List<Product> productList = productRepository.findByProductCategory(categoryName);
    model.addAttribute(categoryName + "ProductList", productList);
  }

  @Override
  @Transactional
  public void getConditionProductList(Model model, String conditionName) {
    List<Product> productList = productRepository.findByProductCondition(conditionName);
    if (conditionName.equals("AllSeason")) {
      model.addAttribute("AllSeasonProductList", productList);
    } else {
      model.addAttribute("SeasonalProductList", productList);
    }
  }

  @Override
  @Transactional
  public void getIdProductProduct(Model model, Long id) {
    Product product = productRepository.findByProductId(id);
    model.addAttribute("product", product);
  }

  @Override
  @Transactional
  public List<Product> getRoasteryProductList(String roastery) {
    return productRepository.findByProductRoastery(roastery);
  }

  @Override
  @Transactional
  public Product findProductById(Long id) {
    return productRepository.findByProductId(id);
  }

  @Override
  @Transactional
  public void deleteProductById(Long id) {
    productRepository.deleteByProductId(id);
  }

  @Override
  @Transactional
  public void saveProduct(ProductDto productDto, MultipartFile imgFile) throws IOException {
    String originalFilename = imgFile.getOriginalFilename();
    String imgName = "";
    String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/images/files/";
    imgName = productDto.productRoastery() + "_" + originalFilename;

    File saveFile = new File(projectPath, imgName);

    imgFile.transferTo(saveFile);
    Product product = Product.builder()
        .productCategory(productDto.productCategory())
        .productName(productDto.productName())
        .productPrice(productDto.productPrice())
        .productDescription(productDto.productDescription())
        .productRoastery(productDto.productRoastery())
        .productCondition(productDto.productCondition())
        .cupNote(productDto.cupNote())
        .imgPath("/images/files/" + imgName)
        .quantity(productDto.quantity())
        .imgName(imgName)
        .build();
    productRepository.save(product);

  }

  @Override
  @Transactional
  public void updateProduct(ProductDto productDto, MultipartFile imgFile) throws IOException {
    productRepository.findById(String.valueOf(productDto.productId())).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
    String originalFilename = imgFile.getOriginalFilename();
    String imgName = "";
    String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/images/files/";
    imgName = productDto.productRoastery() + "_" + originalFilename;

    File saveFile = new File(projectPath, imgName);

    imgFile.transferTo(saveFile);
    Product product = Product.builder()
        .productId(productDto.productId())
        .productCategory(productDto.productCategory())
        .productName(productDto.productName())
        .productPrice(productDto.productPrice())
        .productDescription(productDto.productDescription())
        .productRoastery(productDto.productRoastery())
        .productCondition(productDto.productCondition())
        .cupNote(productDto.cupNote())
        .imgPath("/images/files/" + imgName)
        .quantity(productDto.quantity())
        .imgName(imgName)
        .build();
    productRepository.save(product);
  }

  @Override
  public List<Product> getFilteredProducts(String category, String condition, String roastery) {
    List<Product> productList = new ArrayList<>();
    if (category != null && condition != null) {
      productList = productRepository.findByProductCategoryAndProductCondition(category, condition);
    } else if (category != null) {
      productList = productRepository.findByProductCategory(category);
    } else if (condition != null) {
      productList = productRepository.findByProductCondition(condition);
    } else {
      productList = productRepository.findAll();
    }
    if (roastery != null) {
      return productList.stream().filter(product -> product.getProductRoastery().equals(roastery)).collect(Collectors.toList());
    } else {
      return productList;
    }
  }
}

