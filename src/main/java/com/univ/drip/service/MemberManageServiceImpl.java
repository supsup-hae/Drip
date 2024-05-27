package com.univ.drip.service;

import com.univ.drip.entity.Member;
import com.univ.drip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberManageServiceImpl implements MemberManageService {

  private final MemberRepository memberRepository;

  @Override
  public String registrationMember(Member member) {
    return null;
  }

  @Override
  public String searchMemberSessionInfo(Member member) {
    return null;
  }

  @Override
  public String updateMemberInfo(Member member) {
    return null;
  }

  @Override
  public String loginMember(Member member) {
    return null;
  }
}
