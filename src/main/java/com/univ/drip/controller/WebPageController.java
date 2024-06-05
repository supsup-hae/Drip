package com.univ.drip.controller;

import com.univ.drip.entity.Member;
import com.univ.drip.security.DripUserDetails;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.impl.MemberManageServiceImpl;
import com.univ.drip.service.ProductManageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/page")
public class WebPageController {

  private final ProductManageService productManageService;
  private final MemberManageService memberManageService;

  @Autowired
  public WebPageController(ProductManageService productManageService, MemberManageServiceImpl memberManageService) {
    this.productManageService = productManageService;
    this.memberManageService = memberManageService;
  }

  @GetMapping("/index")
  public String moveToIndex(Model model, HttpSession session) {
    productManageService.getConditionProductList(model, "AllSeason");
    productManageService.getConditionProductList(model, "Seasonal");
    Member member = (Member) session.getAttribute("member");
    if (member == null) {
      memberManageService.generateDefaultMemberAttribute(model);
    } else {
      model.addAttribute("member", member);
    }
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

  @GetMapping("/edit")
  public String moveToEditProfile(Authentication authentication, Model model) {
    DripUserDetails userDetails = (DripUserDetails) authentication.getPrincipal();
    Member member = userDetails.getMember();
    model.addAttribute("member", member);
    return "editProfile";
  }

  @GetMapping("/login")
  public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
      try {
        response.sendRedirect(request.getContextPath() + "/api/page/profile");
        return null;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return "login";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/api/page/index";
  }

  @GetMapping("/profile")
  public String profile(Authentication authentication, Model model) {
    DripUserDetails userDetails = (DripUserDetails) authentication.getPrincipal();
    String currentUserId = authentication.getName(); // Assuming the username is the member ID
    Member member = memberManageService.findMemberById(currentUserId);
    model.addAttribute("member", member);
    return "profile";
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


  @GetMapping("/productInfo/{id}")
  public String productInfo(Model model, @PathVariable String id) {
    productManageService.getIdProductProduct(model, id);
    return "productInfo";
  }

  @GetMapping("/register")
  public String moveToRegister(Model model) {
    memberManageService.generateDefaultMemberAttribute(model);
    return "register";
  }

}
