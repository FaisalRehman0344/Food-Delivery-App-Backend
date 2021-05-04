package com.fooddeliveryservice.app.sockets;


import com.fooddeliveryservice.app.models.LocationModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    @MessageMapping("/shareLocation")
    @SendTo("/getLocation")
    public String greeting(LocationModel locationModel) {
        return "200";
    }
}
