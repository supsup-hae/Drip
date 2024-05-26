package com.univ.drip.controller;

import com.univ.drip.entity.Member;
import com.univ.drip.service.MemberManageService;
import com.univ.drip.service.MemberManageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

  private final MemberManageService memberManageService;

  @Autowired
  public MemberController(MemberManageServiceImpl memberManageService) {
    this.memberManageService = memberManageService;
  }

  @PostMapping("/registration")
  public ResponseEntity<String> registrationMember(@RequestBody Member member) {
    return memberManageService.registrationMember(member);
  }

  @PostMapping("/search")
  public ResponseEntity<String> searchMemberSessionInfo(@RequestBody Member member) {
    return memberManageService.searchMemberSessionInfo(member);
  }

  @PostMapping("/update")
  public ResponseEntity<String> updateMemberInfo(@RequestBody Member member) {
    return memberManageService.updateMemberInfo(member);
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginMember(@RequestBody Member member) {
    return memberManageService.loginMember(member);
  }
}
