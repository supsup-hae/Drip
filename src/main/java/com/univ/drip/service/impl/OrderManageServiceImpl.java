package com.univ.drip.service.impl;

import com.univ.drip.dto.OrderDto;
import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import com.univ.drip.entity.Order;
import com.univ.drip.entity.OrderItem;
import com.univ.drip.repository.CartItemRepository;
import com.univ.drip.repository.CartRepository;
import com.univ.drip.repository.MemberRepository;
import com.univ.drip.repository.OrderItemRepository;
import com.univ.drip.repository.OrderRepository;
import com.univ.drip.service.OrderManageService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderManageServiceImpl implements OrderManageService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  @Autowired
  public OrderManageServiceImpl(MemberRepository memberRepository, CartRepository cartRepository, CartItemRepository cartItemRepository,
      OrderRepository orderRepository,
      OrderItemRepository orderItemRepository) {
    this.cartItemRepository = cartItemRepository;
    this.orderItemRepository = orderItemRepository;
    this.cartRepository = cartRepository;
    this.orderRepository = orderRepository;
  }


  @Override
  @Transactional
  public Order createOrderFromCart(Cart cart, OrderDto orderDto, HttpSession session) {
    Order order = Order.createOrder(cart.getMember());
    orderRepository.save(order);

    List<CartItem> cartItems = cart.getCartItems();
    for (CartItem cartItem : cartItems) {
      OrderItem orderItem = OrderItem.createOrderItem(order, cartItem.getProduct(), cartItem.getCount());
      orderItemRepository.save(orderItem);
    }

    cartItemRepository.deleteByCart(cart);
    cart.getCartItems().clear();
    cartItemRepository.updateCountByCart(0, cart);
    cartRepository.save(cart);

    return order;
  }
}