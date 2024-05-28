package com.univ.drip.controller;

import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.MemberManageServiceImpl;
import com.univ.drip.service.ProductManageService;
import com.univ.drip.service.ProductManageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/page")
public class WebPageController {

  private final ProductManageService productManageService;
  private final MemberManageService memberManageService;

  @Autowired
  public WebPageController(ProductManageServiceImpl productManageService, MemberManageServiceImpl memberManageService) {
    this.productManageService = productManageService;
    this.memberManageService = memberManageService;
  }

  @GetMapping("/index")
  public String moveToIndex(Model model) {
    return "index";
  }

  @GetMapping("/addProduct")
  public String addProduct(Model model) {
    return "addProduct";
  }

  @GetMapping("/cart")
  public String moveToCart(Model model) {
    return "cart";
  }

  @GetMapping("/drip-bag")
  public String moveToDripBag(Model model) {
    return "drip-bag";
  }

  @GetMapping("/editProfile")
  public String moveToEditProfile(Model model) {
    return "editProfile";
  }

  @GetMapping("/login")
  public String moveToLogin(Model model) {
    return "addProduct";
  }

  @GetMapping("/lowkey")
  public String moveToLowkey(Model model) {
    productManageService.getRoasteryProductList(model, "Lowkey");
    return "lowkey";
  }

  @GetMapping("/pastelCoffeeWorks")
  public String moveToPastelCoffeeWorks(Model model) {
    productManageService.getRoasteryProductList(model, "PastelCoffeeWorks");
    return "PastelCoffeeWorks";
  }

  @GetMapping("/peerCoffee")
  public String moveToPeerCoffee(Model model) {
    productManageService.getRoasteryProductList(model, "PeerCoffee");
    return "PeerCoffee";
  }

  @GetMapping("/coffeeHeureum")
  public String moveToCoffeeHeureum(Model model) {
    productManageService.getRoasteryProductList(model, "CoffeeHeureum");
    return "CoffeeHeureum";
  }


  @GetMapping("/productInfo")
  public String productInfo(Model model) {
    return "productInfo";
  }

  @GetMapping("/profile")
  public String moveToProfile(Model model) {
    return "profile";
  }

  @GetMapping("/register")
  public String moveToRegister(Model model) {
    return "register";
  }

}
