package com.fooddeliveryservice.app.repository;

import com.fooddeliveryservice.app.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users findUsersByUsername(String username);
    public boolean existsByUsername(String username);
    public boolean existsByFirstnameAndLastname(String firstname,String lastname);
}
