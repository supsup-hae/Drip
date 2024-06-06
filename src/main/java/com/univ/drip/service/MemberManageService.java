package com.univ.drip.service;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public interface MemberManageService {
  String registrationMember(MemberDto memberDto);

  String searchMemberSessionInfo(Member member);

  String updateMemberInfo(MemberDto memberDto, HttpSession session);

  String loginMember(Model model);

  void generateDefaultMemberAttribute(HttpSession session);

  Member findMemberById(String id);
}
