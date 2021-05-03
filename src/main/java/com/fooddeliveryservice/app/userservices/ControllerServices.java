package com.fooddeliveryservice.app.userservices;

import com.fooddeliveryservice.app.entities.Users;
import com.fooddeliveryservice.app.models.UpdatePasswordModel;
import com.fooddeliveryservice.app.models.UserExistException;
import com.fooddeliveryservice.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.ResponseExtractor;

import java.util.List;

@Service
public class ControllerServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> addUser(Users user){
        if (userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.ok(new UserExistException("User already exists",404));
        } else if (userRepository.existsByFirstnameAndLastname(user.getFirstname(),user.getLastname())){
            return ResponseEntity.ok(new UserExistException("Firstname and lastname already taken",404));
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Users realUser = new Users(user.getId(),user.getFirstname(),user.getLastname(),user.getUsername(),encodedPassword,user.getAddress(),user.getContact(),"ROLE_USER");
        userRepository.save(realUser);
        return ResponseEntity.ok(new UserExistException("SignUp Successful",200));
    }

    public ResponseEntity<?> addAdmin(Users admin) {
        if (userRepository.existsByUsername(admin.getUsername())){
            return ResponseEntity.ok(new UserExistException("User already exists",404));
        } else{
            String encodedPassword = passwordEncoder.encode(admin.getPassword());
            Users realAdmin = new Users(admin.getId(),admin.getFirstname(),admin.getLastname(),admin.getUsername(),encodedPassword,admin.getAddress(),admin.getContact(),"ROLE_ADMIN");
            userRepository.save(realAdmin);
            return ResponseEntity.ok(new UserExistException("SignUp Successful",200));
        }
    }

    public Users login(String username){
        return userRepository.findUsersByUsername(username);
    }

    public ResponseEntity<?> updatePassword(UpdatePasswordModel newUser) {
        Users user = userRepository.findUsersByUsername(newUser.getUsername());
        if (user != null){
            String storedContact = userRepository.findUsersByUsername(newUser.getUsername()).getContact();
            if (storedContact.equals(newUser.getContact())){
                user.setPassword(passwordEncoder.encode(newUser.getPassword()));
                userRepository.save(user);
                return ResponseEntity.ok(new UserExistException("User updated",200));
            }else if (!storedContact.equals(newUser.getContact())){
                return ResponseEntity.ok(new UserExistException("Contact Invalid",404));
            }
        } else {
            return ResponseEntity.ok(new UserExistException("User not found",404));
        }
        return null;
    }

    public ResponseEntity<?> updateUser(Users user) {
        Users oldUser = userRepository.findUsersByUsername(user.getUsername());
            if (!user.getFirstname().isEmpty()){
                oldUser.setFirstname(user.getFirstname());
            } else if (!user.getLastname().isEmpty()){
                oldUser.setLastname(user.getLastname());
            } else if (!user.getAddress().isEmpty()){
                oldUser.setAddress(user.getAddress());
            } else if (!user.getContact().isEmpty()){
                oldUser.setContact(user.getContact());
            } else {
                return ResponseEntity.ok(new UserExistException("No changes found",300));
            }
            userRepository.save(oldUser);
            return ResponseEntity.ok(new UserExistException("User Updated",200));
    }
}
