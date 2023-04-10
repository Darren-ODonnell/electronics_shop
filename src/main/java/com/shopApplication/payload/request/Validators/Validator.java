package com.shopApplication.payload.request.Validators;

import com.shopApplication.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

abstract class Validator {
    protected Validator next;

    abstract void setNext(Validator validator);
    abstract ResponseEntity<?> validate(SignupRequest request);
}