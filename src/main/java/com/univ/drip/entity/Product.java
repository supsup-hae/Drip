package com.univ.drip.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

  @Id
  @Column(name = "productId", nullable = false)
  private String productId;

  @Column(name = "productCategory")
  private String productCategory;

  @Column(name = "productName")
  private String productName;

  @Column(name = "productPrice")
  private Integer productPrice;

  @Lob
  @Column(name = "productDescription")
  private String productDescription;

  @Column(name = "productRoastery")
  private String productRoastery;

  @Column(name = "productsInStock")
  private Long productsInStock;

  @Column(name = "productCondition")
  private String productCondition;

  @Lob
  @Column(name = "cupNote")
  private String cupNote;

  @Lob
  @Column(name = "imgPath")
  private String imgPath;

  @Column(name = "quantity")
  private Integer quantity;

}