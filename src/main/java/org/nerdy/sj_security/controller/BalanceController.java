package org.nerdy.sj_security.controller;

import org.nerdy.sj_security.model.AccountTransactions;
import org.nerdy.sj_security.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getAccountDetail(@RequestParam int id) {

        List<AccountTransactions> accountTransactions = accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);

        if (accountTransactions != null) {
            return accountTransactions;
        }else {
            return null;
        }


    }
}
