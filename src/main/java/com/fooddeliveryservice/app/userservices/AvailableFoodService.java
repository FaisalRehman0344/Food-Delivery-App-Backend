package com.fooddeliveryservice.app.userservices;

import com.fooddeliveryservice.app.entities.AvailableFood;
import com.fooddeliveryservice.app.repository.AvailableFoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableFoodService {
    @Autowired
    private AvailableFoodRepo availableFoodRepo;

    public List<AvailableFood> availableFood(){
        return availableFoodRepo.findAll();
    }

    public String addFood(AvailableFood newFood){
        if (availableFoodRepo.existsAvailableFoodByName(newFood.getName())){
            return "Food already available...";
        } else {
            availableFoodRepo.save(newFood);
            return "Food saved successfully...";
        }
    }

    public String updateFood(AvailableFood food){
        AvailableFood availableFood = availableFoodRepo.findAvailableFoodByName(food.getName());
        if (availableFood != null){
            food.setId(availableFood.getId());
            availableFoodRepo.save(food);
            return "Food updated...";
        } else
            return "Food with this name not found!";
    }

    public String deleteFood(String name){
        if (availableFoodRepo.existsAvailableFoodByName(name)){
            int id = availableFoodRepo.findAvailableFoodByName(name).getId();
            availableFoodRepo.deleteById(id);
            return "Food deleted...";
        } else {
            return "Food not found by given name!";
        }
    }
}