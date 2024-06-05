package com.univ.drip.repository;

import com.univ.drip.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

  Cart findByMember_Id(String id);

}