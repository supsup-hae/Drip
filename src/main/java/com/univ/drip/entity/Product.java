package com.univ.drip.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Table(name = "product")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

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


  @Column(name = "product_condition")
  private String productCondition;

  @Lob
  @Column(name = "cup_note")
  private String cupNote;

  @Lob
  @Column(name = "img_path")
  private String imgPath;

  @Lob
  @Column(name = "img_name")
  private String imgName;

  @Column(name = "quantity")
  private Integer quantity;

  @Builder
  public Product(Long productId, String productCategory, String productName, Integer productPrice, String productDescription,
      String productRoastery,
      String productCondition, String cupNote, String imgPath, String imgName, Integer quantity) {
    this.productId = productId;
    this.productCategory = productCategory;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productDescription = productDescription;
    this.productRoastery = productRoastery;
    this.productCondition = productCondition;
    this.cupNote = cupNote;
    this.imgPath = imgPath;
    this.imgName = imgName;
    this.quantity = quantity;
  }
}