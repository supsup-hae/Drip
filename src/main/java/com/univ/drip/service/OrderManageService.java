package com.univ.drip.service;

import com.univ.drip.dto.OrderDto;
import com.univ.drip.entity.Cart;
import com.univ.drip.entity.Order;
import jakarta.servlet.http.HttpSession;

public interface OrderManageService {

  Order createOrderFromCart(Cart cart, OrderDto orderDto,  HttpSession session);
}
