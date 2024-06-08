package com.univ.drip.service;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface MemberManageService {
  String registrationMember(MemberDto memberDto, RedirectAttributes redirectAttributes);

  String searchMemberSessionInfo(Member member);

  String updateMemberInfo(MemberDto memberDto, HttpSession session);

  void generateDefaultMemberAttribute(HttpSession session);

  Member findMemberById(String id);
}
