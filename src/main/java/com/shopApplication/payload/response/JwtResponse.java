package com.shopApplication.payload.response;

import com.shopApplication.models.Role;

import java.util.List;


public class JwtResponse {
    private String accessToken;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private String shippingAddress;
    private String paymentMethod;
    private List<Role> roles;

    public JwtResponse(String accessToken, Integer id, String username, String shippingAddress, String paymentMethod, List<Role> roles) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

}