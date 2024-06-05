package com.univ.drip.dto;

import com.univ.drip.entity.CartItem;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.univ.drip.entity.Cart}
 */
public record CartDto(int cartId, MemberDto member, int count, List<CartItem> cartItems, LocalDate createDate) implements Serializable {

}