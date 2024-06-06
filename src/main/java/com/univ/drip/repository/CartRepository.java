package com.univ.drip.repository;

import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

  Cart findByMember_Id(String id);

  List<Cart> findByCartItems(CartItem cartItems);

  List<Cart> findByCartId(Cart cart);

  long deleteByCartItems(CartItem cartItems);

  @Transactional
  @Modifying
  @Query("update Cart c set c.count = ?1 where c.cartId = ?2")
  int updateCountByCartId(int count, int cartId);
}