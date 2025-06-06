package com.bakery.repository;

import com.bakery.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}


