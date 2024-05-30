package com.univ.drip.service;

import com.univ.drip.dto.MemberDto;
import com.univ.drip.entity.Member;
import com.univ.drip.entity.Role;
import com.univ.drip.repository.MemberRepository;
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

  @Override
  public String searchMemberSessionInfo(Member member) {
    return null;
  }

  @Override
  public String updateMemberInfo(Member member) {
    return null;
  }

  @Override
  public String loginMember(Member member) {
    return null;
  }

  @Override
  public void generateDefaultMemberAttribute(Model model) {
    model.addAttribute("member", new MemberDto(null, "", "", "", "", "", "", "", "", "", true, Role.GUEST));
  }
}
