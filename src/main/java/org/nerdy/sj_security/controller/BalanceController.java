package org.nerdy.sj_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getAccountDetail() {
        return "HI myBalance";
    }
}
