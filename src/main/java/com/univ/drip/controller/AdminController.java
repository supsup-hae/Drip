package com.univ.drip.controller;

import com.univ.drip.entity.Product;
import com.univ.drip.entity.Role;
import com.univ.drip.security.DripUserDetails;
import com.univ.drip.service.ProductManageService;
import com.univ.drip.service.impl.ProductManageServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/admin")
public class AdminController {

  private final ProductManageService productManageService;

  public AdminController(ProductManageServiceImpl productManageService) {
    this.productManageService = productManageService;
  }

  @GetMapping("/productList/{id}")
  public String userCartPage(@PathVariable("id") String id, Model model, @AuthenticationPrincipal DripUserDetails dripUserDetails) {
    if (dripUserDetails.getMember().getRole().equals(Role.ADMIN)) {
      List<Product> roasteryProductList = productManageService.getRoasteryProductList(id);
      model.addAttribute("roasteryProductList", roasteryProductList);
      return "productList";
    } else {
      return "redirect:/api/page/index";
    }
  }




}
