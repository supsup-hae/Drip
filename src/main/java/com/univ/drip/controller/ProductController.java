package com.univ.drip.controller;

import com.univ.drip.entity.Member;
import com.univ.drip.entity.Product;
import com.univ.drip.service.CartManageService;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.ProductManageService;
import com.univ.drip.service.WebPageManageService;
import com.univ.drip.service.impl.CartManageServiceImpl;
import com.univ.drip.service.impl.MemberManageServiceImpl;
import com.univ.drip.service.impl.ProductManageServiceImpl;
import com.univ.drip.service.impl.WebPageManageServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/api/product")
public class ProductController {

  private final ProductManageService productManageService;
  private final MemberManageService memberManageService;
  private final CartManageService cartManageService;

  private final WebPageManageService webPageManageService;

  @Autowired
  public ProductController(ProductManageServiceImpl productManageService, MemberManageServiceImpl memberManageService,
      CartManageServiceImpl cartManageService, WebPageManageServiceImpl webPageManageService) {
    this.productManageService = productManageService;
    this.memberManageService = memberManageService;
    this.cartManageService = cartManageService;
    this.webPageManageService = webPageManageService;
  }

  @PostMapping("/register")
  public String registerProduct(@RequestBody Product product) {
    return productManageService.registrationProduct(product);
  }

  @PostMapping("/cart/{id}/{itemId}")
  public String addCartItem(@PathVariable("id") String memberId, @PathVariable("itemId") String productId, @RequestParam("amount") int amount, HttpSession session) {
    Member member = memberManageService.findMemberById(memberId);
    Product product = productManageService.findProductById(productId);
    cartManageService.addCart(product, member, amount);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    return "redirect:/api/page/cart/" + memberId;
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
