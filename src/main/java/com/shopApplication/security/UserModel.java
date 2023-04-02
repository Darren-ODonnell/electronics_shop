package com.shopApplication.security;

import com.shopApplication.models.ItemReview;
import com.shopApplication.models.Role;
import com.shopApplication.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 120)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @Size(max = 255)
    @Column(name = "shipping_address")
    private String shippingAddress;

    @Size(max = 255)
    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToMany(mappedBy = "user")
    private Set<ItemReview> itemReviews = new LinkedHashSet<>();

    public UserModel() {
    }
    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User translateModelToUser(){
        User user = new User();
        user.setUsername(this.username);
        user.setShippingAddress(this.shippingAddress);
        user.setPaymentMethod(this.paymentMethod);
        user.setPassword(this.password);
        return user;
    }

}