package com.univ.drip.controller;

import com.univ.drip.dto.OrderDto;
import com.univ.drip.entity.Cart;
import com.univ.drip.entity.Order;
import com.univ.drip.service.CartManageService;
import com.univ.drip.service.OrderManageService;
import com.univ.drip.service.impl.CartManageServiceImpl;
import com.univ.drip.service.impl.OrderManageServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/order")
public class OrderController {

  private final OrderManageService orderManageService;
  private final CartManageService cartManageService;

  @Autowired
  public OrderController(OrderManageServiceImpl orderManageService, CartManageServiceImpl cartManageService) {
    this.orderManageService = orderManageService;
    this.cartManageService = cartManageService;
  }

  @PostMapping("/{cartId}")
  public String createOrder(@PathVariable Long cartId, OrderDto orderDto, Model model, HttpSession session) {
    Cart cart = cartManageService.fintByCartId(String.valueOf(cartId)).orElseThrow();
    Order order = orderManageService.createOrderFromCart(cart, orderDto, session);
    model.addAttribute("order", order);
    return "redirect:/api/page/index";
  }
}
