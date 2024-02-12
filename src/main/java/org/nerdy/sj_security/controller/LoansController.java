package org.nerdy.sj_security.controller;

import org.nerdy.sj_security.model.Loans;
import org.nerdy.sj_security.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @GetMapping("/myLoans")
    public List<Loans> getAccountDetail(@RequestParam int id) {

        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(id);

        if (loans != null) {
            return loans;
        }else {
            return null;
        }

    }
}
