package com.univ.drip.service;

import com.univ.drip.entity.Member;
import org.springframework.http.ResponseEntity;

public interface MemberManageService {
  String registrationMember(Member member);

  String searchMemberSessionInfo(Member member);

  String updateMemberInfo(Member member);

  String loginMember(Member member);
}
