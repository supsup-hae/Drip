package com.univ.drip.service;

import com.univ.drip.entity.Member;
import com.univ.drip.entity.Product;

public interface CartManageService {

  void addCart(Product product, Member member, int count);
}
