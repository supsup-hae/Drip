package com.univ.drip.controller;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.MemberManageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/member")
public class MemberController {

  private final MemberManageService memberManageService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public MemberController(MemberManageServiceImpl memberManageService, PasswordEncoder passwordEncoder) {
    this.memberManageService = memberManageService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/register")
  public String registrationMember(@ModelAttribute MemberDto memberDto) {
    memberManageService.registrationMember(memberDto);
    return "redirect:/api/page/login";
  }

  @PostMapping("/search")
  public String searchMemberSessionInfo(Member member) {
    return memberManageService.searchMemberSessionInfo(member);
  }

  @PostMapping("/update")
  public String updateMemberInfo(@RequestBody Member member) {
    return memberManageService.updateMemberInfo(member);
  }

  @PostMapping("/login")
  public String loginMember(@RequestBody Member member) {
    return memberManageService.loginMember(member);
  }

  @GetMapping("/editProfile")
  public String moveToEditProfile() {
    return "editProfile";
  }


}
