package com.univ.drip.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;


public interface WebPageManageService {

  void getAuthInfo(Authentication authentication, HttpSession session);

}
