package com.shopApplication.payload.request.Validators;

import com.shopApplication.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public class ValidatorChain {
    private Validator chain;

    public void addValidator(Validator validator) {
        if (chain == null) {
            chain = validator;
        } else {
            Validator currentValidator = chain;
            while (currentValidator != null) {
                if (currentValidator instanceof PasswordMatchValidator) {
                    currentValidator.setNext(validator);
                    break;
                } else if (currentValidator instanceof UsernameValidator) {
                    currentValidator.setNext(validator);
                    break;
                } else {
                    currentValidator = currentValidator.next;
                }
            }
        }
    }

    public ResponseEntity<?> validate(SignupRequest request) {
        return chain.validate(request);
    }
}