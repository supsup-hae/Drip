package com.univ.drip.service;

import com.univ.drip.entity.Cart;
import com.univ.drip.entity.CartItem;
import com.univ.drip.entity.Member;
import com.univ.drip.entity.Product;
import java.util.List;

public interface CartManageService {

  void addCart(Product product, Member member, int count);

  Cart findByMemberId(String memberId);

  CartItem findByProductId(String productId);

  List<CartItem> findAllCartItems(Cart cart);

  void deleteCartItem(String productId);
}
