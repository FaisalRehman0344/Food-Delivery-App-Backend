package com.fooddeliveryservice.app.repository;

import com.fooddeliveryservice.app.entities.AvailableFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableFoodRepo extends JpaRepository<AvailableFood,Integer> {
    public Boolean existsAvailableFoodByName(String name);
    public AvailableFood findAvailableFoodByName(String name);
}
