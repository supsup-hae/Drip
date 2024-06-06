package com.univ.drip.service.impl;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import com.univ.drip.entity.Role;
import com.univ.drip.repository.MemberRepository;
import com.univ.drip.service.MemberManageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
@Transactional
public class MemberManageServiceImpl implements MemberManageService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public MemberManageServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void registrationMember(MemberDto memberDto) {
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
    memberRepository.save(member);
  }

  public Member findMemberById(String id) {
    return memberRepository.findById(id).orElseThrow();
  }

  @Override
  public String searchMemberSessionInfo(Member member) {
    return null;
  }

  @Override
  public String updateMemberInfo(Member member, HttpSession session) {
    String encodePassword = passwordEncoder.encode(member.getPassword());
    Member updateMember = Member.builder()
        .id(member.getId())
        .password(encodePassword)
        .email(member.getEmail())
        .name(member.getName())
        .gender(member.getGender())
        .phoneNumber(member.getPhoneNumber())
        .zipCode(member.getZipCode())
        .address(member.getAddress())
        .detailedAddress(member.getDetailedAddress())
        .extraAddress(member.getExtraAddress())
        .role(member.getRole())
        .status(member.getStatus())
        .build();
    memberRepository.save(updateMember);
    session.setAttribute("member", member);
    return "redirect:/api/page/profile";
  }

  @Override
  public String loginMember(Model model) {
    return null;
  }

  @Override
  public void generateDefaultMemberAttribute(HttpSession session) {
    session.setAttribute("member", new MemberDto(null, "", "", "", "", "", "", "", "", "", true, Role.GUEST));
  }

}
