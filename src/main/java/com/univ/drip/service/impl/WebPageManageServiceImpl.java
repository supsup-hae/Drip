package com.univ.drip.service.impl;

import com.univ.drip.entity.Member;
import com.univ.drip.security.DripUserDetails;
import com.univ.drip.service.WebPageManageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WebPageManageServiceImpl implements WebPageManageService {

  @Override
  public void getAuthInfo(Authentication authentication, HttpSession session) {
    DripUserDetails userDetails = (DripUserDetails) authentication.getPrincipal();
    Member member = userDetails.getMember();
    session.setAttribute("member", member);
  }
}
