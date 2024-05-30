package com.univ.drip.dto;

import com.univ.drip.entity.Role;
import java.io.Serializable;

/**
 * DTO for {@link com.univ.drip.entity.Member}
 */
public record MemberDto(String id, String password, String email, String name, String gender, String phoneNumber, String zipCode, String address,
                        String detailedAddress, String extraAddress, Boolean status, Role role) implements
    Serializable {

}