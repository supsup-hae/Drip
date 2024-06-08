package com.univ.drip.controller;

import com.univ.drip.dto.ProductDto;
import com.univ.drip.entity.Member;
import com.univ.drip.entity.Product;
import com.univ.drip.service.CartManageService;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.ProductManageService;
import com.univ.drip.service.impl.CartManageServiceImpl;
import com.univ.drip.service.impl.MemberManageServiceImpl;
import com.univ.drip.service.impl.ProductManageServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/api/product")
public class ProductController {

  private final ProductManageService productManageService;
  private final MemberManageService memberManageService;
  private final CartManageService cartManageService;

  @Autowired
  public ProductController(ProductManageServiceImpl productManageService, MemberManageServiceImpl memberManageService,
      CartManageServiceImpl cartManageService) {
    this.productManageService = productManageService;
    this.memberManageService = memberManageService;
    this.cartManageService = cartManageService;
  }

  @PostMapping("/add")
  public String addProduct(ProductDto productDto, @RequestParam("imgFile") MultipartFile imgFile, Model model, HttpSession session)
      throws IOException {
    Member member = (Member) session.getAttribute("member");
    productManageService.saveProduct(productDto, imgFile);
    List<Product> roasteryProductList = productManageService.getRoasteryProductList(member.getId());
    model.addAttribute("roasteryProductList", roasteryProductList);
    return "redirect:/api/admin/productList/" + member.getId();
  }

  @PostMapping("/edit")
  public String editProduct(ProductDto productDto, @RequestParam("imgFile") MultipartFile imgFile, Model model, HttpSession session)
      throws IOException {
    Member member = (Member) session.getAttribute("member");
    productManageService.updateProduct(productDto, imgFile);
    List<Product> roasteryProductList = productManageService.getRoasteryProductList(member.getId());
    model.addAttribute("roasteryProductList", roasteryProductList);
    return "redirect:/api/admin/productList/" + member.getId();
  }

  @PostMapping("/cart/{id}/{itemId}")
  public String addCartItem(@PathVariable("id") String memberId, @PathVariable("itemId") Long productId, @RequestParam("amount") int amount,
      HttpSession session) {
    Member member = memberManageService.findMemberById(memberId);
    Product product = productManageService.findProductById(productId);
    cartManageService.addCart(product, member, amount);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return "redirect:/api/page/cart/" + memberId;
  }

  @PostMapping("/edit/{productId}")
  public String editProduct(@PathVariable Long productId, Model model) {
    Product product = productManageService.findProductById(productId);
    model.addAttribute("product", product);
    return "editProduct";
  }

  @PostMapping("/delete/{productId}")
  public String deleteProduct(@PathVariable Long productId, HttpSession session) {
    productManageService.deleteProductById(productId);
    Member member = (Member) session.getAttribute("member");
    return "redirect:/api/admin/productList/" + member.getId();
  }

  @GetMapping("/filter/{roastery}")
  public String getProducts(@RequestParam(required = false) String category,
      @RequestParam(required = false) String condition,
      Model model, @PathVariable String roastery) {
    List<Product> products = productManageService.getFilteredProducts(category, condition, roastery);
    model.addAttribute("productList", products);
    model.addAttribute("selectedCategory", category);
    model.addAttribute("selectedCondition", condition);
    return "/" + roastery;
  }

  @GetMapping("/filter/drip-bag")
  public String getDripBagProducts(@RequestParam(required = false) String condition,
      Model model) {
    List<Product> products = productManageService.getFilteredProducts("drip-bag", condition, null);
    model.addAttribute("productList", products);
    model.addAttribute("selectedCondition", condition);
    return "/drip-bag";
  }

  @GetMapping("/cart")
  public String moveToCart() {
    return "cart";
  }


}
