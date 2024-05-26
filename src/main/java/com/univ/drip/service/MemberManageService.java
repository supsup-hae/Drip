package com.univ.drip.service;

import com.univ.drip.entity.Member;
import org.springframework.http.ResponseEntity;

public interface MemberManageService {
  ResponseEntity<String> registrationMember(Member member);

  ResponseEntity<String> searchMemberSessionInfo(Member member);

  ResponseEntity<String> updateMemberInfo(Member member);

  ResponseEntity<String> loginMember(Member member);
}
