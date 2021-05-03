package com.fooddeliveryservice.app.userservices;

import com.fooddeliveryservice.app.entities.FoodOrdered;
import com.fooddeliveryservice.app.repository.FoodOrderedRepo;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOrderedService {

    @Autowired
    private FoodOrderedRepo repo;

    public List<FoodOrdered> getAll(){
        return repo.findAll();
    }

    public List<FoodOrdered> getOrderedFood(String username){
        return repo.findFoodOrderedsByUsername(username);
    }

    public String orderFood(FoodOrdered foodOrdered){
        repo.save(foodOrdered);
        return "Food ordered successfully";
    }
    public void deleteOrder(FoodOrdered foodOrdered){
        int id = repo.findFoodOrderedByUsernameAndDateTime(foodOrdered.getUsername(),foodOrdered.getDateTime()).getId();
        repo.deleteById(id);

    }
}
