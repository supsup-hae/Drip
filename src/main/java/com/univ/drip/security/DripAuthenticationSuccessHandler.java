package com.univ.drip.security;

import com.univ.drip.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class DripAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    DripUserDetails userDetails = (DripUserDetails) authentication.getPrincipal();
    Member member = userDetails.getMember();
    request.getSession().setAttribute("member", member);
    response.sendRedirect("/api/page/index");
  }
}