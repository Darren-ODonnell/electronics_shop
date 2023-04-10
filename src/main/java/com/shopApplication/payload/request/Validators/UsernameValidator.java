package com.shopApplication.payload.request.Validators;

import com.shopApplication.payload.request.SignupRequest;
import com.shopApplication.payload.response.MessageResponse;
import com.shopApplication.services.UserService;
import org.springframework.http.ResponseEntity;

public class UsernameValidator extends Validator {
    private UserService userService;

    public UsernameValidator(UserService userService) {
        this.userService = userService;
    }

    public void setNext(Validator validator) {
        next = validator;
    }

    public ResponseEntity<?> validate(SignupRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (next != null) {
            return next.validate(request);
        }

        return null;
    }
}