package com.univ.drip.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.univ.drip.entity.Product}
 */
public record ProductDto(Long productId, String productCategory, String productName, Integer productPrice, String productDescription,
                         String productRoastery, String productCondition, String cupNote, String imgPath,
                         Integer quantity, String imgName) implements
    Serializable {

}