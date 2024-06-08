package com.univ.drip.repository;

import com.univ.drip.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findByMember_Id(String id);
}