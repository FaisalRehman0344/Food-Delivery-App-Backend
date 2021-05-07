package com.fooddeliveryservice.app.userservices;

import com.fooddeliveryservice.app.entities.User;
import com.fooddeliveryservice.app.models.UpdatePasswordModel;
import com.fooddeliveryservice.app.models.UserExistException;
import com.fooddeliveryservice.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControllerServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> addUser(User user){
        if (userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.ok(new UserExistException("User already exists",404));
        } else if (userRepository.existsByFirstnameAndLastname(user.getFirstname(),user.getLastname())){
            return ResponseEntity.ok(new UserExistException("Firstname and lastname already taken",404));
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User realUser = new User(user.getId(),user.getFirstname(),user.getLastname(),user.getUsername(),encodedPassword,user.getAddress(),user.getContact(),"ROLE_USER");
        userRepository.save(realUser);
        return ResponseEntity.ok(new UserExistException("SignUp Successful",200));
    }

    public ResponseEntity<?> addAdmin(User admin) {
        if (userRepository.existsByUsername(admin.getUsername())){
            return ResponseEntity.ok(new UserExistException("User already exists",404));
        } else{
            String encodedPassword = passwordEncoder.encode(admin.getPassword());
            User realAdmin = new User(admin.getId(),admin.getFirstname(),admin.getLastname(),admin.getUsername(),encodedPassword,admin.getAddress(),admin.getContact(),"ROLE_ADMIN");
            userRepository.save(realAdmin);
            return ResponseEntity.ok(new UserExistException("SignUp Successful",200));
        }
    }

    public User login(String username){
        return userRepository.findUserByUsername(username);
    }

    public ResponseEntity<?> updatePassword(UpdatePasswordModel newUser) {
        User user = userRepository.findUserByUsername(newUser.getUsername());
        if (user != null){
            String storedContact = userRepository.findUserByUsername(newUser.getUsername()).getContact();
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

    public ResponseEntity<?> updateUser(User user) {
        User oldUser = userRepository.findUserByUsername(user.getUsername());
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

    public List<User> getUsers(){
        return userRepository.findUsersByRole("ROLE_USER");
    }
    public List<User> getAdmins(){
        return userRepository.findUsersByRole("ROLE_ADMIN");
    }
}
