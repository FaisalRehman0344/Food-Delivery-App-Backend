package com.fooddeliveryservice.app.repository;

import com.fooddeliveryservice.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
     User findUserByUsername(String username);
     boolean existsByUsername(String username);
     boolean existsByFirstnameAndLastname(String firstname,String lastname);
     List<User> findUsersByRole(String role);
}
