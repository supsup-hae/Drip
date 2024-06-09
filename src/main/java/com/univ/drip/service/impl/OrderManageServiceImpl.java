package com.univ.drip.service.impl;

import com.univ.drip.dto.OrderDto;
import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import com.univ.drip.entity.Member;
import com.univ.drip.entity.Order;
import com.univ.drip.entity.OrderItem;
import com.univ.drip.entity.Product;
import com.univ.drip.repository.CartItemRepository;
import com.univ.drip.repository.CartRepository;
import com.univ.drip.repository.MemberRepository;
import com.univ.drip.repository.OrderItemRepository;
import com.univ.drip.repository.OrderRepository;
import com.univ.drip.repository.ProductRepository;
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
  private final ProductRepository productRepository;

  @Autowired
  public OrderManageServiceImpl(MemberRepository memberRepository, CartRepository cartRepository, CartItemRepository cartItemRepository,
      OrderRepository orderRepository,
      OrderItemRepository orderItemRepository,
      ProductRepository productRepository) {
    this.cartItemRepository = cartItemRepository;
    this.orderItemRepository = orderItemRepository;
    this.cartRepository = cartRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }


  @Override
  @Transactional
  public Order createOrderFromCart(Cart cart, OrderDto orderDto, HttpSession session) {
    Order order = Order.createOrder(cart.getMember());
    orderRepository.save(order);

    List<CartItem> cartItems = cart.getCartItems();
    for (CartItem cartItem : cartItems) {
      OrderItem orderItem = OrderItem.createOrderItem(order, cartItem.getProduct(), cartItem.getCount());
      Product product = productRepository.findById(String.valueOf(cartItem.getProduct().getProductId())).orElseThrow();
      productRepository.updateQuantityByProductId(product.getQuantity() - cartItem.getCount(), cartItem.getProduct().getProductId());
      orderItemRepository.save(orderItem);
    }
    cartRepository.updateCountByCartId(0, cart.getCartId());
    cartItemRepository.deleteByCart(cart);
    cart.getCartItems().clear();
    cartRepository.save(cart);

    return order;
  }

  @Override
  public List<Order> findOrderByMemberId(HttpSession session) {
    Member member = (Member) session.getAttribute("member");
    return orderRepository.findByMember_Id(member.getId());
  }
}