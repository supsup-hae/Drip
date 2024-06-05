package com.univ.drip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int cartId;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "member_id")
  private Member member;

  private int count;

  @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
  private List<CartItem> cartItems = new ArrayList<>();

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate createDate;

  @PrePersist
  public void createDate() {
    this.createDate = LocalDate.now();
  }

  public static Cart createCart(Member member) {
    Cart cart = new Cart();
    cart.setCount(0);
    cart.setMember(member);
    return cart;
  }
}