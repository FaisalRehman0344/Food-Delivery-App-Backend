package com.fooddeliveryservice.app.controllers;

import com.fooddeliveryservice.app.detailservices.MyUserDetailsService;
import com.fooddeliveryservice.app.entities.User;
import com.fooddeliveryservice.app.jwtutil.JwtUtil;
import com.fooddeliveryservice.app.models.AuthenticationRequest;
import com.fooddeliveryservice.app.models.AuthenticationResponse;
import com.fooddeliveryservice.app.models.UpdatePasswordModel;
import com.fooddeliveryservice.app.userservices.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginControllers {

    @Autowired
    ControllerServices controllerServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtilToken;

    @RequestMapping(method = RequestMethod.POST,value="/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        } catch (BadCredentialsException e){
            System.out.println(e);
            throw new BadCredentialsException("Bad Credentials "+e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());

        final String jwt = jwtUtilToken.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt,jwtUtilToken.extractExpiration(jwt)));
    }

    @GetMapping(value = "/user/getUser")
    public User getUser(@RequestHeader("Authorization") String token){
        String jwt = token.substring(8);
        String username = jwtUtilToken.extractUsername(jwt);
        return controllerServices.login(username);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> userSignup(@RequestBody User user) {
        return controllerServices.addUser(user);
    }

    @PostMapping(value = "/admin/signup")
    public ResponseEntity<?> adminSignup(@RequestBody User admin){
        return controllerServices.addAdmin(admin);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordModel newUser){
        return controllerServices.updatePassword(newUser);
    }
    @PostMapping(value = "/user/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        return controllerServices.updateUser(user);
    }

    @GetMapping(value = "/admin/getUsers")
    public List<User> getUsers(){
        return controllerServices.getUsers();
    }
    @GetMapping(value = "/admin/getAdmins")
    public List<User> getAdmins(){
        return controllerServices.getAdmins();
    }

}
