package com.univ.drip.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/page")
public class WebPageController {

  @GetMapping("/index")
  public String index() {
    return "index";
  }


}
