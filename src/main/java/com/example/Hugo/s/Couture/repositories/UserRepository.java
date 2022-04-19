package com.example.Hugo.s.Couture.repositories;

import com.example.Hugo.s.Couture.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findUserByEmailOrUsername(String email, String password);
     User findByEmail(String email);
}
