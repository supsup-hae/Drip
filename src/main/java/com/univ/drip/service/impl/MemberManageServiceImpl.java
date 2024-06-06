package com.univ.drip.service.impl;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import com.univ.drip.entity.Role;
import com.univ.drip.repository.MemberRepository;
import com.univ.drip.service.MemberManageService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Slf4j
@Service
public class MemberManageServiceImpl implements MemberManageService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public MemberManageServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public String registrationMember(MemberDto memberDto) {
    Member member = Member.builder()
        .id(memberDto.id())
        .password(passwordEncoder.encode(memberDto.password()))
        .email(memberDto.email())
        .name(memberDto.name())
        .gender(memberDto.gender())
        .phoneNumber(memberDto.phoneNumber())
        .zipCode(memberDto.zipCode())
        .address(memberDto.address())
        .detailedAddress(memberDto.detailedAddress())
        .extraAddress(memberDto.extraAddress())
        .role(Role.USER)
        .status(true)
        .build();
    log.info("memberDto =" + memberDto);
    log.info("member =" + member.toString());
    memberRepository.save(member);
    return "redirect:/api/page/login";
  }

  @Transactional
  public Member findMemberById(String id) {
    return memberRepository.findById(id).orElseThrow();
  }

  @Transactional
  @Override
  public String searchMemberSessionInfo(Member member) {
    return null;
  }

  @Transactional
  @Override
  public String updateMemberInfo(MemberDto memberDto, HttpSession session) {
    String encodePassword = passwordEncoder.encode(memberDto.password());
    Member updateMember = Member.builder()
        .id(memberDto.id())
        .password(encodePassword)
        .email(memberDto.email())
        .name(memberDto.name())
        .gender(memberDto.gender())
        .phoneNumber(memberDto.phoneNumber())
        .zipCode(memberDto.zipCode())
        .address(memberDto.address())
        .detailedAddress(memberDto.detailedAddress())
        .extraAddress(memberDto.extraAddress())
        .role(memberDto.role())
        .status(memberDto.status())
        .build();
    memberRepository.save(updateMember);
    session.setAttribute("member", updateMember);
    return "redirect:/api/page/profile";
  }

  @Override
  public String loginMember(Model model) {
    return null;
  }

  @Transactional
  @Override
  public void generateDefaultMemberAttribute(HttpSession session) {
    session.setAttribute("memberDto", new MemberDto(null, "", "", "", "", "", "", "", "", "", true, Role.GUEST));
  }

}
