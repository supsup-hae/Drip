package com.univ.drip.service;

import com.univ.drip.dto.OrderDto;
import com.univ.drip.entity.Cart;
import com.univ.drip.entity.Order;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public interface OrderManageService {

  Order createOrderFromCart(Cart cart, OrderDto orderDto,  HttpSession session);

  List<Order> findOrderByMemberId(HttpSession session) ;
}
