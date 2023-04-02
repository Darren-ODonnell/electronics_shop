package com.shopApplication.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 1, max = 40)
    private String password;

    @NotBlank
    @Size(max = 50)
    private String paymentMethod;

    @NotBlank
    @Size(max = 50)
    private String shippingAddress;

    private Set<String> role;


    @NotBlank
    @Size(min = 1, max = 40)
    private String passwordConfirm;

    public String getUsername() {       return username;    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {        return passwordConfirm;    }

    public void setPasswordConfirm(String passwordConfirm) {        this.passwordConfirm = passwordConfirm;    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}