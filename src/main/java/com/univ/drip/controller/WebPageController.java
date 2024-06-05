package com.univ.drip.controller;

import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import com.univ.drip.entity.Member;
import com.univ.drip.security.DripUserDetails;
import com.univ.drip.service.CartManageService;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.ProductManageService;
import com.univ.drip.service.WebPageManageService;
import com.univ.drip.service.impl.CartManageServiceImpl;
import com.univ.drip.service.impl.MemberManageServiceImpl;
import com.univ.drip.service.impl.WebPageManageServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
  private final WebPageManageService webPageManageService;
  private final CartManageService cartManageService;

  @Autowired
  public WebPageController(ProductManageService productManageService, MemberManageServiceImpl memberManageService,
      WebPageManageServiceImpl webPageManageService, CartManageServiceImpl cartManageService) {
    this.productManageService = productManageService;
    this.memberManageService = memberManageService;
    this.webPageManageService = webPageManageService;
    this.cartManageService = cartManageService;
  }

  @GetMapping("/index")
  public String moveToIndex(Authentication authentication, Model model) {
    productManageService.getConditionProductList(model, "AllSeason");
    productManageService.getConditionProductList(model, "Seasonal");
    webPageManageService.getAuthInfo(authentication, model);
    return "index";
  }

  @GetMapping("/addProduct")
  public String addProduct(Authentication authentication, Model model) {
    webPageManageService.getAuthInfo(authentication, model);
    return "addProduct";
  }

  @GetMapping("/cart")
  public String moveToCartPage(Authentication authentication) {
    DripUserDetails userDetails = (DripUserDetails) authentication.getPrincipal();
    return "redirect:/api/page/cart/" + userDetails.getMember().getId();
  }

  @GetMapping("/cart/{id}")
  public String userCartPage(@PathVariable("id") String id, Model model, @AuthenticationPrincipal DripUserDetails dripUserDetails) {
    if (dripUserDetails.getMember().getId().equals(id)) {

      Member member = memberManageService.findMemberById(id);
      Cart userCart = cartManageService.findByMemberId(member.getId());

      List<CartItem> cartItemList = cartManageService.findAllCartItems(userCart);

      int totalPrice = 0;
      for (CartItem cartitem : cartItemList) {
        totalPrice += cartitem.getCount() * cartitem.getProduct().getProductPrice();
      }
      return setCartPageAttribute(model, dripUserDetails, userCart, cartItemList, totalPrice);
    }
    else {
      return "redirect:/main";
    }
  }

  @GetMapping("/cart/{id}/{cartItemId}/delete")
  public String deleteCartItem(@PathVariable("id") String id, @PathVariable("cartItemId") String productId, Model model,
      @AuthenticationPrincipal DripUserDetails dripUserDetails) {
    if (dripUserDetails.getMember().getId().equals(id)) {
      CartItem cartItem = cartManageService.findByProductId(productId);

      cartManageService.deleteCartItem(productId);

      Cart userCart = cartManageService.findByMemberId(dripUserDetails.getMember().getId());

      List<CartItem> cartItemList = cartManageService.findAllCartItems(userCart);

      int totalPrice = 0;
      for (CartItem cartitem : cartItemList) {
        totalPrice += cartitem.getCount() * cartitem.getProduct().getProductPrice();
      }

      userCart.setCount(userCart.getCount() - cartItem.getCount());

      return setCartPageAttribute(model, dripUserDetails, userCart, cartItemList, totalPrice);
    }
    // 로그인 id와 장바구니 삭제하려는 유저의 id가 같지 않는 경우
    else {
      return "redirect:/main";
    }
  }

  @GetMapping("/drip-bag")
  public String moveToDripBag(Authentication authentication, Model model) {
    webPageManageService.getAuthInfo(authentication, model);
    return "drip-bag";
  }

  @GetMapping("/edit")
  public String moveToEditProfil(Authentication authentication, Model model) {
    webPageManageService.getAuthInfo(authentication, model);
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
    webPageManageService.getAuthInfo(authentication, model);
    return "profile";
  }

  @GetMapping("/lowkey")
  public String moveToLowkey(Authentication authentication, Model model) {
    webPageManageService.getAuthInfo(authentication, model);
    productManageService.getRoasteryProductList(model, "Lowkey");
    return "lowkey";
  }

  @GetMapping("/pastelCoffeeWorks")
  public String moveToPastelCoffeeWorks(Authentication authentication, Model model) {
    webPageManageService.getAuthInfo(authentication, model);
    productManageService.getRoasteryProductList(model, "PastelCoffeeWorks");
    return "PastelCoffeeWorks";
  }

  @GetMapping("/peerCoffee")
  public String moveToPeerCoffee(Authentication authentication, Model model) {
    webPageManageService.getAuthInfo(authentication, model);
    productManageService.getRoasteryProductList(model, "PeerCoffee");
    return "PeerCoffee";
  }

  @GetMapping("/coffeeHeureum")
  public String moveToCoffeeHeureum(Authentication authentication, Model model) {
    webPageManageService.getAuthInfo(authentication, model);
    productManageService.getRoasteryProductList(model, "CoffeeHeureum");
    return "CoffeeHeureum";
  }


  @GetMapping("/productInfo/{id}")
  public String productInfo(Model model, @PathVariable String id, Authentication authentication) {
    webPageManageService.getAuthInfo(authentication, model);
    productManageService.getIdProductProduct(model, id);
    return "productInfo";
  }

  @GetMapping("/register")
  public String moveToRegister(Authentication authentication, Model model) {
    webPageManageService.getAuthInfo(authentication, model);
    memberManageService.generateDefaultMemberAttribute(model);
    return "register";
  }

  private String setCartPageAttribute(Model model, @AuthenticationPrincipal DripUserDetails dripUserDetails,
      Cart userCart, List<CartItem> cartItemList, int totalPrice) {
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("totalCount", userCart.getCount());
    model.addAttribute("cartItems", cartItemList);
    model.addAttribute("shoppingCost", cartItemList.stream().map(item -> item.getProduct().getProductRoastery()).distinct().count() * 3000);
    model.addAttribute("member", dripUserDetails.getMember());
    return "/cart";
  }

}