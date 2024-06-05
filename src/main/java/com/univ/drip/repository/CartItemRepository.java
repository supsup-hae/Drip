package com.univ.drip.repository;

import com.univ.drip.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
  CartItem findByCart_CartIdAndProduct_ProductId(int cartId, String productId);
}