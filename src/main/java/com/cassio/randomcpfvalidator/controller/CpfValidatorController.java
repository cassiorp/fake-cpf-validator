package com.cassio.randomcpfvalidator.controller;

import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class CpfValidatorController {

    @GetMapping("/users/cpf/{cpf}")
    public ResponseEntity<Boolean> validate(@PathVariable String cpf) {
        return new ResponseEntity<>(validateCpf(cpf), OK);
    }

    public Boolean validateCpf(String cpf) {
        CPFValidator validator = new CPFValidator();
        Random random = new Random();
        try {
            validator.assertValid(cpf);
            return random.nextBoolean();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
