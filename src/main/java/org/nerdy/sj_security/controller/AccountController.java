package org.nerdy.sj_security.controller;

import lombok.RequiredArgsConstructor;
import org.nerdy.sj_security.model.Accounts;
import org.nerdy.sj_security.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetail(@RequestParam int id) {
        Accounts accounts = accountsRepository.findByCustomerId(id);

        if(accounts != null) {
            return accounts;
        }else {
            return null;
        }

    }
}
