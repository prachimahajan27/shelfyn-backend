package com.example.shelfyn.repository;

import com.example.shelfyn.Entity.Product;
import com.example.shelfyn.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    List<Product> findByUserId(Long userId);
     Optional<User> findByEmail(String email);

}