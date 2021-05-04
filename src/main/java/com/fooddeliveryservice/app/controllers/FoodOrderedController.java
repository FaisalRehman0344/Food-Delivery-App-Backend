package com.fooddeliveryservice.app.controllers;

import com.fooddeliveryservice.app.entities.FoodOrdered;
import com.fooddeliveryservice.app.userservices.FoodOrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodOrderedController {
    @Autowired
    private FoodOrderedService foodOrderedService;

    @GetMapping(value = "/admin/getOrderedFood")
    public List<FoodOrdered> getAll(){
        return foodOrderedService.getAll();
    }

    @GetMapping(value = "/user/getOrderedFood/{username}")
    public List<FoodOrdered> getOrderedFood(@PathVariable String username){
        return foodOrderedService.getOrderedFood(username);
    }

    @PostMapping(value = "/user/orderFood")
    public String orderFood(@RequestBody FoodOrdered foodOrdered){
        return foodOrderedService.orderFood(foodOrdered);
    }

    @DeleteMapping(value = "/user/deleteOrder")
    public void deleteOrder(@RequestBody FoodOrdered foodOrdered){
        foodOrderedService.deleteOrder(foodOrdered);
    }

}
