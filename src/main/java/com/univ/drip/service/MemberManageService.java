package com.univ.drip.service;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import org.springframework.ui.Model;

public interface MemberManageService {
  void registrationMember(MemberDto memberDto);

  String searchMemberSessionInfo(Member member);

  String updateMemberInfo(Member member, Model model);

  String loginMember(Model model);

  void generateDefaultMemberAttribute(Model model);

  Member findMemberById(String id);
}
