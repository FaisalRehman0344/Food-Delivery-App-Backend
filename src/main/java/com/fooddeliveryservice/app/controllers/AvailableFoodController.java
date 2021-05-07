package com.fooddeliveryservice.app.controllers;

import com.fooddeliveryservice.app.entities.AvailableFood;
import com.fooddeliveryservice.app.userservices.AvailableFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AvailableFoodController {

    @Autowired
    private AvailableFoodService availableFoodService;

    @GetMapping(value = "/user/getAllAvailableFoods")
    public List<AvailableFood> getAll(){
        return availableFoodService.availableFood();
    }
    @PostMapping(value = "/admin/addFood")
    public String addFood(@RequestBody AvailableFood newFood){
        return availableFoodService.addFood(newFood);
    }
    @PutMapping(value = "/admin/updateFood")
    public String updateFood(@RequestBody AvailableFood newFood){
        return availableFoodService.updateFood(newFood);
    }
    @DeleteMapping(value = "/admin/deleteFood/{foodName}")
    public String deleteFood(@PathVariable String foodName){
        System.out.println(foodName);
        return availableFoodService.deleteFood(foodName);
    }
}
