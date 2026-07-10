package jar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jar.model.User;
import jar.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userRepository.save(user);
        return "User Registered Successfully!";
    }

    
    @PostMapping("/login")
public String login(@RequestBody User loginRequest) {
    try {
        System.out.println("DEBUG: Email: " + loginRequest.getEmail());
        
        User user = userRepository.findFirstByEmail(loginRequest.getEmail());
        
        if (user == null) {
            System.out.println("DEBUG: User not found in DB");
            return "Invalid!";
        }
        
        System.out.println("DEBUG: Password from DB: " + user.getPassword());
        
        if (user.getPassword().equals(loginRequest.getPassword())) {
            return "Login Successful!";
        } else {
            return "Invalid!";
        }
    } catch (Exception e) {
        e.printStackTrace(); 
        return "Error occurred: " + e.getMessage();
    }
}
}