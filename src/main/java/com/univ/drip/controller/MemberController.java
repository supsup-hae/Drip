package com.univ.drip.controller;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.impl.MemberManageServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/api/member")
public class MemberController {

  private final MemberManageService memberManageService;

  @Autowired
  public MemberController(MemberManageServiceImpl memberManageService) {
    this.memberManageService = memberManageService;
  }

  @PostMapping("/register")
  public String registrationMember(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes) {
     return memberManageService.registrationMember(memberDto, redirectAttributes);
  }

  @PostMapping("/search")
  public String searchMemberSessionInfo(Member member) {
    return memberManageService.searchMemberSessionInfo(member);
  }

  @PostMapping("/update")
  public String updateMemberInfo(@ModelAttribute MemberDto memberDto, HttpSession session) {
    return memberManageService.updateMemberInfo(memberDto, session);
  }

  @GetMapping("/login")
  public String loginMember() {
    return "login";
  }

  @GetMapping("/logout")
  public String logoutMember() {
    return "redirect:api/page/index";
  }

  @GetMapping("/editProfile")
  public String moveToEditProfile() {
    return "editProfile";
  }

}
