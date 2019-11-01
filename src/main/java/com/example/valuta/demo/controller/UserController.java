package com.example.valuta.demo.controller;
import com.example.valuta.demo.entity.user.Role;
import com.example.valuta.demo.entity.user.User;
import com.example.valuta.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepository.findByUsername(user.getUsername());
                if(userFromDb!=null){
                    model.put("message","User exists!");
                    return "registration";
                }
                user.setActive(true);
                user.setRoles(Collections.singleton(Role.USER));
                userRepository.save(user);
        return "redirect:/login";
    }


}
