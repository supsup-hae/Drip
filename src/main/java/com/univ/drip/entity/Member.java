package com.univ.drip.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "name")
  private String name;

  @Column(name = "gender", length = 50)
  private String gender;

  @Column(name = "phone_number", length = 50)
  private String phoneNumber;

  @Column(name = "zip_code", length = 50)
  private String zipCode;

  @Column(name = "address")
  private String address;

  @Column(name = "detailed_address")
  private String detailedAddress;

  @Column(name = "extra_address")
  private String extraAddress;

  @Column(name = "status")
  private Boolean status;

  @Column(name = "role", length = 50)
  private String role;

}