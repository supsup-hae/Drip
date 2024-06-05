package com.univ.drip.repository;

import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import com.univ.drip.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
  CartItem findByCart_CartIdAndProduct_ProductId(int cartId, String productId);

  List<CartItem> findByCart(Cart cart);

  CartItem findByProduct_ProductId(String productId);

  long deleteByProduct(Product product);
}