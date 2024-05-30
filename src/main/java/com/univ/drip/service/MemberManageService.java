package com.univ.drip.service;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import org.springframework.ui.Model;

public interface MemberManageService {
  void registrationMember(MemberDto memberDto);

  String searchMemberSessionInfo(Member member);

  String updateMemberInfo(Member member);

  String loginMember(Member member);

  void generateDefaultMemberAttribute(Model model);

}
