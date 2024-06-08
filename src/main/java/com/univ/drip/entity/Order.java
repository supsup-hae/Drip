package com.univ.drip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int orderId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
  private List<OrderItem> orderItems = new ArrayList<>();

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate orderDate;

  @PrePersist
  public void orderDate() {
    this.orderDate = LocalDate.now();
  }

  public static Order createOrder(Member member) {
    Order order = new Order();
    order.setMember(member);
    return order;
  }
}