package com.univ.drip.controller;

import com.univ.drip.entity.Member;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.MemberManageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/member")
public class MemberController {

  private final MemberManageService memberManageService;

  @Autowired
  public MemberController(MemberManageServiceImpl memberManageService) {
    this.memberManageService = memberManageService;
  }

  @PostMapping("/registration")
  public String registrationMember(@RequestBody Member member) {
    return memberManageService.registrationMember(member);
  }

  @PostMapping("/search")
  public String searchMemberSessionInfo(@RequestBody Member member) {
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
