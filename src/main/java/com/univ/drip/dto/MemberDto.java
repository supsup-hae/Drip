package com.univ.drip.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.univ.drip.entity.Member}
 */
public record MemberDto(Long id, String password, String email, String name, String gender, String phoneNumber, String zipCode, String address,
                        String detailedAddress, String extraAddress, Boolean status, String role) implements
    Serializable {

}