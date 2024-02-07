package org.nerdy.sj_security.controller;

import org.nerdy.sj_security.model.Customer;
import org.nerdy.sj_security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
            String encode = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(encode);

            save = customerRepository.save(customer);
            response = ResponseEntity.status(HttpStatus.CREATED)
                    .body("계정 생성 완료");

        }catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CREATED)
                    .body("계정 생성 실패");
        }

        return response;
    }


}
