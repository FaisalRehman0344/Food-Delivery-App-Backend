package com.fooddeliveryservice.app.repository;


import com.fooddeliveryservice.app.entities.FoodOrdered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderedRepo extends JpaRepository<FoodOrdered,Integer> {
     FoodOrdered findFoodOrderedByUsernameAndDateTime(String username, String dateTime);
     List<FoodOrdered> findFoodOrderedsByUsername(String username);
}
