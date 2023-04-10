package com.shopApplication.payload.request.Validators;

import com.shopApplication.payload.request.SignupRequest;
import com.shopApplication.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public class PasswordMatchValidator extends Validator {

    public void setNext(Validator validator) {
        next = validator;
    }

    public ResponseEntity<?> validate(SignupRequest request) {
        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Passwords do not match!"));
        }

        if (next != null) {
            return next.validate(request);
        }

        return null;
    }
}