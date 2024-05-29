package com.univ.drip.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "product")
public class Product {

  @Id
  @Column(name = "product_id", nullable = false)
  private String productId;

  @Column(name = "product_category")
  private String productCategory;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "product_price")
  private Integer productPrice;

  @Lob
  @Column(name = "product_description")
  private String productDescription;

  @Column(name = "product_roastery")
  private String productRoastery;

  @Column(name = "products_in_stock")
  private Long productsInStock;

  @Column(name = "product_condition")
  private String productCondition;

  @Lob
  @Column(name = "cup_note")
  private String cupNote;

  @Lob
  @Column(name = "img_path")
  private String imgPath;

  @Column(name = "quantity")
  private Integer quantity;

}