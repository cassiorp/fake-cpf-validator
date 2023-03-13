package com.cassio.randomcpfvalidator.controller;

import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class CpfValidatorController {

    @GetMapping("/user/validate/{cpf}")
    public ResponseEntity<ValidationDTO> validate(@PathVariable String cpf) {
        ValidationDTO validationDTO = new ValidationDTO(validateCpf(cpf));
        return ResponseEntity
                .status(validationDTO.getStatus().equals("ABLE_TO_VOTE") ? OK : NOT_FOUND)
                .body(validationDTO);
    }

    private String validateCpf(String cpf) {
        CPFValidator validator = new CPFValidator();
        Random random = new Random();
        try {
            validator.assertValid(cpf);
            return random.nextBoolean() ? "ABLE_TO_VOTE" : "UNABLE_TO_VOTE";
        } catch (RuntimeException e) {
            return "UNABLE_TO_VOTE";
        }
    }
}
