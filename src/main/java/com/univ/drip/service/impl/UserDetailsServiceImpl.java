package com.univ.drip.service.impl;

import com.univ.drip.entity.Member;
import com.univ.drip.repository.MemberRepository;
import com.univ.drip.security.DripUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Autowired
  public UserDetailsServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + username));
    return new DripUserDetails(member);
  }
}
