package com.univ.drip.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

  @Id
  @Column(name = "member_id", nullable = false)
  private String id;

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

  @Enumerated(EnumType.STRING)
  @Column(name = "role", length = 50, nullable = false)
  private Role role;

  @Builder
  public Member(String id, String password, String email, String name, String gender, String phoneNumber, String zipCode, String address,
      String detailedAddress, String extraAddress, Boolean status, Role role) {
    this.id = id;
    this.password = password;
    this.email = email;
    this.name = name;
    this.gender = gender;
    this.phoneNumber = phoneNumber;
    this.zipCode = zipCode;
    this.address = address;
    this.detailedAddress = detailedAddress;
    this.extraAddress = extraAddress;
    this.status = status;
    this.role = role;
  }


}