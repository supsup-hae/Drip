package com.univ.drip.controller;

import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import com.univ.drip.entity.Member;
import com.univ.drip.entity.Role;
import com.univ.drip.security.DripUserDetails;
import com.univ.drip.service.CartManageService;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.ProductManageService;
import com.univ.drip.service.impl.CartManageServiceImpl;
import com.univ.drip.service.impl.MemberManageServiceImpl;
import com.univ.drip.service.impl.ProductManageServiceImpl;
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
  private final CartManageService cartManageService;

  @Autowired
  public WebPageController(ProductManageServiceImpl productManageService, MemberManageServiceImpl memberManageService,
      CartManageServiceImpl cartManageService) {
    this.productManageService = productManageService;
    this.memberManageService = memberManageService;
    this.cartManageService = cartManageService;
  }

  @GetMapping("/")
  public String index() {
    return "redirect:/api/page/index";
  }

  @GetMapping("/index")
  public String moveToIndex(Model model, HttpSession session) {
    productManageService.getConditionProductList(model, "AllSeason");
    productManageService.getConditionProductList(model, "Seasonal");
    Member member = (Member) session.getAttribute("member");
    if (member != null) {
      session.setAttribute("member", member);
      log.info("Member 활성화");
    }
    return "index";
  }

  @GetMapping("/addProduct")
  public String addProduct() {
    return "addProduct";
  }

  @GetMapping("/cart")
  public String moveToCartPage(Authentication authentication) {
    DripUserDetails userDetails = (DripUserDetails) authentication.getPrincipal();
    if (userDetails.getMember().getRole().equals(Role.ADMIN)) {
      return "redirect:/api/admin/productList/" + userDetails.getMember().getId();
    } else {
      return "redirect:/api/page/cart/" + userDetails.getMember().getId();
    }
  }

  @GetMapping("/cart/{id}")
  public String memberCartPage(@PathVariable("id") String id, Model model, @AuthenticationPrincipal DripUserDetails dripUserDetails) {
    if (dripUserDetails.getMember().getId().equals(id)) {

      Member member = memberManageService.findMemberById(id);
      Cart memberCart = cartManageService.findByMemberId(member.getId());
      if (memberCart == null) {
        memberCart = Cart.createCart(member);
        cartManageService.saveCartInfo(memberCart);
      }
      List<CartItem> cartItemList = cartManageService.findAllCartItems(memberCart);

      int totalPrice = 0;
      for (CartItem cartitem : cartItemList) {
        totalPrice += cartitem.getCount() * cartitem.getProduct().getProductPrice();
      }
      return setCartPageAttribute(model, dripUserDetails, memberCart, cartItemList, totalPrice);
    } else {
      return "redirect:/api/page/index";
    }
  }

  @GetMapping("/cart/{id}/{cartItemId}/delete")
  public String deleteCartItem(@PathVariable("id") String id, @PathVariable("cartItemId") Long productId, Model model,
      @AuthenticationPrincipal DripUserDetails dripUserDetails) {
    if (dripUserDetails.getMember().getId().equals(id)) {
      CartItem cartItem = cartManageService.findByProductId(productId);
      cartManageService.deleteCartItem(productId);

      Cart memberCart = cartManageService.findByMemberId(dripUserDetails.getMember().getId());
      List<CartItem> cartItemList = cartManageService.findAllCartItems(memberCart);

      int totalPrice = 0;
      for (CartItem cartitem : cartItemList) {
        totalPrice += cartitem.getCount() * cartitem.getProduct().getProductPrice();
      }

      memberCart.setCount(memberCart.getCount() - cartItem.getCount());
      cartManageService.updateCartInfo(memberCart);
      return setCartPageAttribute(model, dripUserDetails, memberCart, cartItemList, totalPrice);
    }
    // 로그인 id와 장바구니 삭제하려는 유저의 id가 같지 않는 경우
    else {
      return "redirect:/api/page/index";
    }
  }

  @GetMapping("/edit")
  public String moveToEditProfile() {
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
  public String profile() {
    return "profile";
  }

  @GetMapping("/Lowkey")
  public String moveToLowkey(Model model) {
    productManageService.getRoasteryProductList(model, "Lowkey");
    model.addAttribute("roastery", "Lowkey");
    return "lowkey";
  }

  @GetMapping("/PastelCoffeeWorks")
  public String moveToPastelCoffeeWorks(Model model) {
    productManageService.getRoasteryProductList(model, "PastelCoffeeWorks");
    model.addAttribute("roastery", "PastelCoffeeWorks");
    return "PastelCoffeeWorks";
  }

  @GetMapping("/PeerCoffee")
  public String moveToPeerCoffee(Model model) {
    productManageService.getRoasteryProductList(model, "PeerCoffee");
    model.addAttribute("roastery", "PeerCoffee");
    return "PeerCoffee";
  }

  @GetMapping("/CoffeeHeureum")
  public String moveToCoffeeHeureum(Model model) {
    if (model.getAttribute("productList") == null) {
      productManageService.getRoasteryProductList(model, "CoffeeHeureum");
    }
    model.addAttribute("roastery", "CoffeeHeureum");
    return "CoffeeHeureum";
  }

  @GetMapping("/drip-bag")
  public String moveToDripBag(Model model) {
    productManageService.getCategoryProductList(model, "DripBag");
    return "drip-bag";
  }


  @GetMapping("/productInfo/{id}")
  public String productInfo(Model model, @PathVariable Long id, HttpSession session) {
    Member member = (Member) session.getAttribute("member");
    if (member == null) {
      memberManageService.generateDefaultMemberAttribute(session);
    } else {
      session.setAttribute("member", member);
    }
    productManageService.getIdProductProduct(model, id);
    return "productInfo";
  }

  @GetMapping("/register")
  public String moveToRegister(HttpSession session) {
    memberManageService.generateDefaultMemberAttribute(session);
    return "register";
  }

  @GetMapping("/order")
  public String moveToOrder(Model model, @AuthenticationPrincipal DripUserDetails dripUserDetails) {
    Member member = memberManageService.findMemberById(dripUserDetails.getMember().getId());
    Cart memberCart = cartManageService.findByMemberId(member.getId());
    List<CartItem> cartItemList = cartManageService.findAllCartItems(memberCart);
    if (cartItemList.isEmpty()) {
      model.addAttribute("empty", true);
      return "redirect:/api/page/cart";
    }
    int totalPrice = 0;
    for (CartItem cartitem : cartItemList) {
      totalPrice += cartitem.getCount() * cartitem.getProduct().getProductPrice();
    }
    model.addAttribute("cart", memberCart);
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("cartItems", cartItemList);
    model.addAttribute("shoppingCost", cartItemList.stream().map(item -> item.getProduct().getProductRoastery()).distinct().count() * 3000);
    return "order";
  }


  private String setCartPageAttribute(Model model, @AuthenticationPrincipal DripUserDetails dripUserDetails,
      Cart memberCart, List<CartItem> cartItemList, int totalPrice) {
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("totalCount", memberCart.getCount());
    model.addAttribute("cartItems", cartItemList);
    model.addAttribute("shoppingCost", cartItemList.stream().map(item -> item.getProduct().getProductRoastery()).distinct().count() * 3000);
    model.addAttribute("member", dripUserDetails.getMember());
    return "/cart";
  }

}