package com.univ.drip.service;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;


public interface WebPageManageService {

  void getAuthInfo(Authentication authentication, Model model);

}
