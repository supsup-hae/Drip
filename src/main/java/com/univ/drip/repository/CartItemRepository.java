package com.univ.drip.repository;

import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import com.univ.drip.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
  CartItem findByCart_CartIdAndProduct_ProductId(int cartId, Long productId);

  List<CartItem> findByCart(Cart cart);

  CartItem findByProduct_ProductId(Long productId);

  void deleteByProduct(Product product);

  void deleteByCart(Cart cart);

  @Transactional
  @Modifying
  @Query("update CartItem c set c.count = ?1 where c.cart = ?2")
  int updateCountByCart(int count, Cart cart);
}