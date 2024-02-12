package org.nerdy.sj_security.controller;

import org.nerdy.sj_security.model.Customer;
import org.nerdy.sj_security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Customer customer) throws RuntimeException {

        Customer save = null;
        ResponseEntity response = null;

        try{
            String encode = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(encode);
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            save = customerRepository.save(customer);

            response = ResponseEntity.status(HttpStatus.CREATED)
                    .body("계정 생성 완료");

        }catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("계정 생성 실패 " + e.getMessage());
        }

        return response;
    }


    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }

    }


}
