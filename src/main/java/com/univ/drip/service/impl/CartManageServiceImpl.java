package com.univ.drip.service.impl;

import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import com.univ.drip.entity.Member;
import com.univ.drip.entity.Product;
import com.univ.drip.repository.CartItemRepository;
import com.univ.drip.repository.CartRepository;
import com.univ.drip.repository.ProductRepository;
import com.univ.drip.service.CartManageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartManageServiceImpl implements CartManageService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;

  @Autowired
  public CartManageServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
    this.cartRepository = cartRepository;
    this.cartItemRepository = cartItemRepository;
    this.productRepository = productRepository;
  }

  @Override
  @Transactional
  public void addCart(Product newProduct, Member member, int amount) {
    // 유저 id로 해당 유저의 장바구니 찾기
    Cart cart = cartRepository.findByMember_Id(member.getId());

    // 장바구니가 존재하지 않는다면
    if (cart == null) {
      cart = Cart.createCart(member);
      cartRepository.save(cart);
    }

    Product product = productRepository.findByProductId(newProduct.getProductId());
    CartItem cartItem = cartItemRepository.findByCart_CartIdAndProduct_ProductId(cart.getCartId(), product.getProductId());

    // 상품이 장바구니에 존재하지 않는다면 카트상품 생성 후 추가
    if (cartItem == null) {
      cartItem = CartItem.createCartItem(cart, product, amount);
      cartItemRepository.save(cartItem);
    } else {
      cartItem.addCount(amount);
      cartItemRepository.save(cartItem);
    }

    // 카트 상품 총 개수 증가
    cart.setCount(cart.getCount() + amount);
  }

  @Override
  @Transactional
  public Cart findByMemberId(String memberId) {
    return cartRepository.findByMember_Id(memberId);
  }

  @Override
  @Transactional
  public CartItem findByProductId(String productId) {
    return cartItemRepository.findByProduct_ProductId(productId);
  }

  @Override
  @Transactional
  public List<CartItem> findAllCartItems(Cart cart) {
    return cartItemRepository.findByCart(cart);
  }

  @Override
  @Transactional
  public void deleteCartItem(String productId) {
    cartItemRepository.deleteByProduct(productRepository.findByProductId(productId));
  }

  @Override
  @Transactional
  public void updateCartInfo(Cart userCart) {
    cartRepository.updateCountByCartId(userCart.getCount(), userCart.getCartId());
  }

}
