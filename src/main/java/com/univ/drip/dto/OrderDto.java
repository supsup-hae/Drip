package com.univ.drip.dto;

import java.io.Serializable;

public record OrderDto(
    String name,
    String phoneNumber,
    String zipCode,
    String address,
    String detailedAddress,
    String extraAddress,
    String email,
    String paymentMethod,
    String deliveryName,
    String deliveryPhoneNumber,
    String deliveryZipCode,
    String deliveryAddress,
    String deliveryDetailedAddress,
    String deliveryExtraAddress
) implements Serializable {

}