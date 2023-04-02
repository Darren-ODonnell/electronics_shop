package com.shopApplication.repositories;


import com.shopApplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Integer id);
    Boolean existsByUsername(String username);
    boolean existsById(Integer id);

}
